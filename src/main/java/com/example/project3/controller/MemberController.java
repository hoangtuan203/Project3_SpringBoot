package com.example.project3.controller;
import java.util.Date;
import java.security.SecureRandom;
import java.sql.Timestamp;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.project3.models.Member;
import com.example.project3.service.EmailService;
import com.example.project3.service.MemberService;
import com.example.project3.models.ThongTinSD;
import java.security.SecureRandom;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

@Controller
public class MemberController {
    private static final String CHARACTERS = "0123456789";
    private static final int KEY_LENGTH = 8;
    private MemberService memberService;
    @Autowired
    private EmailService emailService;

    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }
    @GetMapping("/")
    public String index() {
        return "login";
    }
    public String getMethodName(@RequestParam String param) {
        return new String();
    }
    
    @GetMapping("/login")
    public String login(Model model) {
        return "login";
    }
    @PostMapping("/index")
    public String datCho(@RequestParam String ngay, @RequestParam String gio, @RequestParam String idMember,@RequestParam  String maTBHiden,RedirectAttributes redirectAttributes) throws ParseException {
        String dateString = ngay+" "+gio; 
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = sdf.parse(dateString); // Chuyển đổi chuỗi sang đối tượng Date
        Timestamp timestamp = new Timestamp(date.getTime()); // Chuyển đổi đối tượng Date sang Timestamp
        ThongTinSD ttsd = new ThongTinSD();
        int maTTSD = Integer.parseInt(generateRandomKey());
        ttsd.setMaTT(maTTSD);
        ttsd.setMaTB(Integer.parseInt(maTBHiden));
        ttsd.setMaTV(Integer.parseInt(idMember));
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

//    @GetMapping("/register")
//    public String register(Model model) {
//        model.addAttribute("members", new Member());
//        return "register";
//    }

    @GetMapping("/newform")
    public String showForm() {
        return "newform"; // Trả về tên của file template Thymeleaf (new-form.html)
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
                                                  "Password của " + ma + " là "+memberService.getPasswordByMaTV(ma).getPassword());
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
        return "newform";
    }

    // Phương thức để kiểm tra email có đúng định dạng hay không

    private boolean isValidmaTV(String ma) {
        // Kiểm tra xem số điện thoại có 10 số không
        return ma != null && ma.matches("^\\d{10}$");
    }
    // xu ly dang ky tai khoan
    

    
    
}
