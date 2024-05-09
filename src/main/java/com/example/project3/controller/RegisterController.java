package com.example.project3.controller;

import com.example.project3.models.Member;
import com.example.project3.service.RegisterService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


@Controller

public class RegisterController {
    private static final String EMAIL_REGEX = "^[A-Za-z0-9+_.-]+@(.+)$";

    // Biểu thức chính quy cho mật khẩu (ít nhất 8 ký tự và ít nhất một chữ cái và một số)
    private static final String PASSWORD_REGEX = "^.{1,10}$";

    private static final String PHONE_REGEX =  "^0\\d{9}$";

    private final RegisterService registerService;

    public RegisterController(RegisterService registerService) {
        this.registerService = registerService;
    }

    @GetMapping("/register")
    public String registerForm(Model model) {
        model.addAttribute("member", new Member());
        return "register";
    }

    @PostMapping("/register")
    public String registerSubmit(@ModelAttribute("member") @Validated Member member, BindingResult bindingResult, Model model) {
        // Kiểm tra nếu có lỗi nhập liệu từ phía Spring Validation
//        if (bindingResult.hasErrors()) {
//            return "register"; // Trả về trang đăng ký với các thông báo lỗi
//        }
//
        if (registerService.isMemberExit(member.getMaTV())) {
            model.addAttribute("idError", "Member ID already exists");
            return "register";
        }
        if(member.getTenTV().equals("")){
            model.addAttribute("nameNull", "Please enter name");
            return "register";
        }

        if(member.getSdt().equals("")){
            model.addAttribute("phoneNull", "Please enter phone");
            return "register";
        }

        if(!isValidePhone(member.getSdt())){
            model.addAttribute("phoneError", "Nhập phone đúng định dạng");
            return "register";
        }


        // Kiểm tra tính hợp lệ của email và mật khẩu
        if (!isValidEmail(member.getEmail())) {
            model.addAttribute("emailError", "Invalid email format");
            return "register";
        }
//
//

        if(member.getPassword().equals("")){
            model.addAttribute("passNull", "Please enter password");
            return "register";
        }

        if (!isValidPassword(member.getPassword())) {
            model.addAttribute("passwordError", "Password có tối đa 10 kí tự");
            return "register";
        }

        registerService.saveMember(member);
        return "redirect:/login";
    }


    // Phương thức để kiểm tra tính hợp lệ của email
    private boolean isValidEmail(String email) {
        return email != null && email.matches(EMAIL_REGEX);
    }

    // Phương thức để kiểm tra tính hợp lệ của mật khẩu
    private boolean isValidPassword(String password) {
        return password != null && password.matches(PASSWORD_REGEX);
    }
    private boolean isValidePhone(String phone){
        return phone.matches(PHONE_REGEX);
    }
}
