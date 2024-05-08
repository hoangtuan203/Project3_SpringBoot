package com.example.project3.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import jakarta.servlet.http.HttpSession;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.example.project3.models.Member;
import com.example.project3.models.Thietbi;
import com.example.project3.models.ThongtinSD;
import com.example.project3.models.Xuly;
import com.example.project3.repository.MemberRepository;
import com.example.project3.repository.ThietBiRepository;
import com.example.project3.repository.ThongTinSDRepository;
import com.example.project3.repository.XuLyRepository;
import com.example.project3.service.EmailService;
import com.example.project3.service.MemberService;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

@Controller
public class MemberController {

    private MemberService memberService;
    @Autowired
    private EmailService emailService;
    private MemberRepository memBerRepository;
    private XuLyRepository xuLyRepository;
    private ThongTinSDRepository thongTinSDRepository;
    private ThietBiRepository thietBiRepository;

    public MemberController(MemberService memberService, MemberRepository memBerRepository, XuLyRepository xuLyRepository, ThongTinSDRepository thongTinSDRepository, ThietBiRepository thietBiRepository) {
        this.memberService = memberService;
        this.memBerRepository = memBerRepository;
        this.xuLyRepository = xuLyRepository;
        this.thongTinSDRepository = thongTinSDRepository;
        this.thietBiRepository = thietBiRepository;
    }

    @GetMapping("/")
    public String index() {
        return "login";
    }

    public String getMethodName(@RequestParam String param) {
        return new String();
    }

//    @GetMapping("/login")
//    public String login(Model model) {
//        return "login";
//    }

    @GetMapping("/loginSuccessful")
    public String loginSuccessful(Model model) {

        return "loginSuccessful";
    }

//    @GetMapping("/register")
//    public String register(Model model) {
//        model.addAttribute("members", new Member());
//        return "register";
//    }
    @GetMapping("/forgotPassword")
    public String showForm() {
        return "forgotPassword";
    }

    @GetMapping("/doimatkhau")
    public String showResetPassword() {
        return "doimatkhau";
    }

    @GetMapping("/hosothanhvien")
    public String showProfile(Model model, @RequestParam("maTV") int maTV) {
        Member tv = memBerRepository.findById(maTV);
        
        if (tv != null) {
            model.addAttribute("thanhVien", tv);
            model.addAttribute("member", tv);
        }
        
        return "hosothanhvien";
    }

    @GetMapping("/trangthaivipham")
    public String trangthaivppage(Model model, @RequestParam("maTV") String maTV) {
        try {
            int maThanhVien = Integer.parseInt(maTV);
            Member tv = new Member();
            tv.setMaTV(maThanhVien);

            Member tv2 = memBerRepository.findById(maThanhVien);
            // Truy vấn danh sách Xuly của thành viên từ Repository
            List<Xuly> xuLyList = xuLyRepository.findByThanhVien(tv);

            if (!xuLyList.isEmpty()) {
                // Đưa danh sách các Xuly vào model để hiển thị trên view
                model.addAttribute("xuLyList", xuLyList);
                model.addAttribute("member", tv2);
                return "trangthaivipham";
            } else {
                model.addAttribute("errorMessage", "Không tìm thấy thông tin xử lý cho thành viên có mã " + maTV);
                return "trangthaivipham";
            }
        } catch (NumberFormatException e) {
            model.addAttribute("errorMessage", "Mã thành viên không hợp lệ: " + maTV);
            return "trangthaivipham";
        }
    }

    @GetMapping("/thietbidangmuon")
    public String tbdangmuonpage(Model model, @RequestParam("maTV") String maTV) {
        if (maTV != null && !maTV.isEmpty()) {
            try {
                int maTVInt = Integer.parseInt(maTV);
                Member tv = new Member();
                tv.setMaTV(maTVInt);
                Member tv3 = memBerRepository.findById(maTVInt);

                List<ThongtinSD> userList = thongTinSDRepository.findByThanhVien(tv);
                if (!userList.isEmpty()) {
                    model.addAttribute("thongTinList", userList); // Thêm danh sách ThongtinSD vào model
                    model.addAttribute("member", tv3);

                    return "thietbidangmuon"; // Trả về view để hiển thị thông tin
                } else {
                    return "thietbidangmuon"; // Trả về view với thông báo không có thông tin
                }
            } catch (NumberFormatException e) {
                System.err.println("Invalid maTV format: " + maTV);
            }
        }
        return "thietbidangmuon"; // Trả về view mặc định nếu có lỗi
    }

    @GetMapping("/datchothietbi")
    public String datchotbpage(Model model, @RequestParam("maTV") String maTV) {
        // Lấy danh sách tất cả các thiết bị từ cơ sở dữ liệu
        int maTVInt = Integer.parseInt(maTV);
        Member tv3 = memBerRepository.findById(maTVInt);
        List<Thietbi> danhSachThietBi = thietBiRepository.findAll();
        // Truyền danh sách các thiết bị qua model
        model.addAttribute("thietBi", danhSachThietBi);
        model.addAttribute("member", tv3);
        return "datchothietbi";
    }

    @PostMapping("/submit-form")
    public String submitForm(@RequestParam String matv, Model model) {
        // Kiểm tra email có hợp lệ không
        if (isValidmaTV(matv)) {
            // model.addAttribute("message", matv);

            int ma = Integer.parseInt(matv);
            if (memberService.getPasswordByMaTV(ma) != null) {
                // gửi maillllll
                emailService.sendSimpleMessage(memberService.getPasswordByMaTV(ma).getEmail(),
                        "Cấp lại password",
                        "Password của " + ma + " là " + memberService.getPasswordByMaTV(ma).getPassword());
                model.addAttribute("message",
                        "đã gửi mail vui lòng check mail");
            } else {
                model.addAttribute("message",
                        "bạn nhập sai mã thành viên");
            }
        } else {
            // Nếu không hợp lệ, truyền thông báo lỗi vào model để hiển thị
            model.addAttribute("message",
                    matv);
        }
        // Trả về tên của trang HTML hiện tại
        return "forgotPassword";
    }

    // Phương thức để kiểm tra email có đúng định dạng hay không
    private boolean isValidmaTV(String ma) {
        // Kiểm tra xem số điện thoại có 10 số không
        return ma != null && ma.matches("^\\d{10}$");
    }
    // xu ly dang ky tai khoan


//    @PostMapping("/login")
//    public String loginMember(@RequestParam int maTV, @RequestParam String password, Model model) {
//        Member member = memberService.loginMember(maTV, password);
//        if (member != null) {
//            System.out.println(member);
//            model.addAttribute("member", member); // member là đối tượng thành viên đã đăng nhập thành công
//
//            return "index";
//        } else {
//
//            return "redirect:/login?error";
//        }
//    }

}
