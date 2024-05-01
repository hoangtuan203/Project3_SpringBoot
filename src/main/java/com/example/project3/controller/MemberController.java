package com.example.project3.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.project3.models.Member;
import com.example.project3.service.EmailService;
import com.example.project3.service.MemberService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

@Controller
public class MemberController {
    private MemberService memberService;
    @Autowired
    private EmailService emailService;

    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping("/login")
    public String listMember(Model model) {
        model.addAttribute("members", memberService.getAllMember());
        return "test";
    }

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

}
