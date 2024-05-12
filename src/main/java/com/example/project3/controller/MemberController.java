package com.example.project3.controller;
import java.util.Date;
import java.security.SecureRandom;
import java.sql.Timestamp;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import jakarta.servlet.http.HttpSession;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.example.project3.models.Member;
import com.example.project3.models.Xuly;
import com.example.project3.repository.MemberRepository;
import com.example.project3.repository.ThietBiRepository;
import com.example.project3.repository.ThongTinSDRepository;
import com.example.project3.repository.XuLyRepository;
import com.example.project3.service.EmailService;
import com.example.project3.service.MemberService;
import com.example.project3.models.ThongtinSD;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

@Controller
public class MemberController {

    private static final String CHARACTERS = "0123456789";
    private static final int KEY_LENGTH = 8;

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
    @PostMapping("/index")
    public String datCho(@RequestParam String ngay, @RequestParam String gio, @RequestParam String idMember,@RequestParam  String maTBHiden,RedirectAttributes redirectAttributes) throws ParseException {
        String dateString = ngay+" "+gio; 
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = sdf.parse(dateString); // Chuyển đổi chuỗi sang đối tượng Date
        Timestamp timestamp = new Timestamp(date.getTime()); // Chuyển đổi đối tượng Date sang Timestamp
        ThongtinSD ttsd = new ThongtinSD();
        int maTTSD = Integer.parseInt(generateRandomKey());
        ttsd.setMaTT(maTTSD);
//        ttsd.setMaTB(Integer.parseInt(maTBHiden));
//        ttsd.setMaTV(Integer.parseInt(idMember));
        ttsd.setTgDatCho(timestamp);
        memberService.insert(ttsd);
        int mtv =Integer.parseInt(idMember);
        System.out.println(mtv);
        Member member = memberService.getMemberById(mtv);

        // model.addAttribute("member", member);
        // System.out.println(member.getTenTV());
        redirectAttributes.addFlashAttribute("member", member);
        return "redirect:/index";
        
    }   
    public  String generateRandomKey() {
        SecureRandom secureRandom = new SecureRandom();
        StringBuilder stringBuilder = new StringBuilder(KEY_LENGTH);

        for (int i = 0; i < KEY_LENGTH; i++) {
            int randomIndex = secureRandom.nextInt(CHARACTERS.length());
            char randomChar = CHARACTERS.charAt(randomIndex);
            stringBuilder.append(randomChar);
        }

        return stringBuilder.toString();

    }
    @GetMapping("/loginSuccessful")
    public String loginSuccessful(Model model) {

        return "loginSuccessful";

    }

    @GetMapping("/forgotPassword")
    public String showForm() {
        return "forgotPassword";
    }

    @GetMapping("/doimatkhau")
    public String showResetPassword(Model model, @RequestParam("maTV") String maTV) {
        int maThanhVien = Integer.parseInt(maTV);

        Member tv2 = memBerRepository.findById(maThanhVien).orElse(null);;
        model.addAttribute("member", tv2);

        return "doimatkhau";
    }

    @PostMapping("/reserPassword")
    public String changePassword(@RequestParam("currentPassword") String currentPassword,
            @RequestParam("newPassword") String newPassword,
            @RequestParam("confirmNewPassword") String confirmNewPassword,
            Model model, HttpSession session) {

        int maTV = (int) session.getAttribute("loggedInMaTV");

        if (!newPassword.equals(confirmNewPassword)) {
            model.addAttribute("error", "Mật khẩu mới không khớp.");
            Member tv = memBerRepository.findById(maTV).orElse(null);;
            model.addAttribute("member", tv);
            return "doimatkhau";
        }

        if (maTV != 0) {
            boolean passwordChanged = memberService.changePassword(maTV, currentPassword, newPassword);
            if (passwordChanged) {
                Member tv2 = memBerRepository.findById(maTV).orElse(null);;
                model.addAttribute("member", tv2);
                model.addAttribute("successMessage", "Thay đổi mật khẩu thành công.");

                return "doimatkhau"; 
            } else {
                Member tv3 = memBerRepository.findById(maTV).orElse(null);;
                model.addAttribute("member", tv3);
                model.addAttribute("error", "Mật khẩu hiện tại không đúng.");
                return "doimatkhau";
            }
        }

        Member tv4 = memBerRepository.findById(maTV).orElse(null);;
        model.addAttribute("member", tv4);
        return "doimatkhau";
    }

//    
    @GetMapping("/hosothanhvien")
    public String showProfile(Model model, @RequestParam("maTV") int maTV) {
        Member tv = memBerRepository.findById(maTV).orElse(null);;

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

            Member tv2 = memBerRepository.findById(maThanhVien).orElse(null);;
            List<Xuly> xuLyList = xuLyRepository.findByThanhVien(tv);

            if (!xuLyList.isEmpty()) {
                model.addAttribute("xuLyList", xuLyList);
                model.addAttribute("member", tv2);
                return "trangthaivipham";
            } else {
                model.addAttribute("errorMessage", "Không tìm thấy thông tin xử lý cho thành viên có mã " + maTV);
                model.addAttribute("member", tv2);
                return "trangthaivipham";
            }
        } catch (NumberFormatException e) {
            model.addAttribute("errorMessage", "Mã thành viên không hợp lệ: " + maTV);
            return "index";
        }
    }

    @GetMapping("/thietbidangmuon")
    public String tbdangmuonpage(Model model, @RequestParam("maTV") String maTV) {
        if (maTV != null && !maTV.isEmpty()) {
            try {
                int maTVInt = Integer.parseInt(maTV);
                Member tv = new Member();
                tv.setMaTV(maTVInt);
                Member tv3 = memBerRepository.findById(maTVInt).orElse(null);;

                List<ThongtinSD> userList = thongTinSDRepository.findByThanhVien(tv);
                if (!userList.isEmpty()) {
                    model.addAttribute("thongTinList", userList);
                    model.addAttribute("member", tv3);

                    return "thietbidangmuon";
                } else {
                    model.addAttribute("member", tv3);
                    return "thietbidangmuon";
                }
            } catch (NumberFormatException e) {
                System.err.println("Invalid maTV format: " + maTV);
            }
        }
        return "thietbidangmuon";
    }

    @GetMapping("/datchothietbi")
    public String datchotbpage(Model model, @RequestParam("maTV") String maTV) {
        if (maTV != null && !maTV.isEmpty()) {
            try {
                int maTVInt = Integer.parseInt(maTV);
                Member tv = new Member();
                tv.setMaTV(maTVInt);
                Member tv3 = memBerRepository.findById(maTVInt).orElse(null);;

                List<ThongtinSD> userList = thongTinSDRepository.findByThanhVien(tv);
                if (!userList.isEmpty()) {
                    model.addAttribute("thongTinList", userList);
                    model.addAttribute("member", tv3);

                    return "datchothietbi";
                } else {
                    model.addAttribute("member", tv3);
                    return "datchothietbi";
                }
            } catch (NumberFormatException e) {
                System.err.println("Invalid maTV format: " + maTV);
            }
        }
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

//   
}
