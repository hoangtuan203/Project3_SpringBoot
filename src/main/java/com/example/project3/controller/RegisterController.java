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
    private static final String EMAIL_REGEX =
            "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
    private static final String PASSWORD_REGEX = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$";

    @Autowired
    RegisterService registerService;
    @Autowired
    public void setMemberService(RegisterService registerService) {
        this.registerService = registerService;
    }
    @GetMapping("/register")
    public String register(Model model) {
        model.addAttribute("member", new Member());
        return "register";
    }
    @PostMapping("/register/save")
    public String register(@Validated @ModelAttribute("member") Member member, BindingResult result, Model model, RedirectAttributes redirectAttributes) {
        if (!isValidEmail(member.getEmail())) {
            result.rejectValue("email", null, "Invalid email format");
        }
        if (!isValidPassword(member.getPassword())) {
            result.rejectValue("password", null, "Password must meet the criteria");
        }

        Member existingMember = registerService.findMemberByEmail(member.getEmail());
        if (existingMember != null) {
            result.rejectValue("email", null, "Email already exists");
        }

        if (result.hasErrors()) {
            model.addAttribute("member", member);
            return "register";
        }

        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(member.getPassword());
        member.setPassword(encodedPassword);

        // Lưu thành viên vào cơ sở dữ liệu
        registerService.saveMember(member);

        // Thêm thông báo thành công vào đối tượng RedirectAttributes
        redirectAttributes.addFlashAttribute("successMessage", "Registration successful!");

        // Chuyển hướng sau khi đăng ký thành công
        return "redirect:/register";
    }
    //regex

    public static boolean isValidEmail(String email) {
        Pattern pattern = Pattern.compile(EMAIL_REGEX);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    public static boolean isValidPassword(String password) {
        Pattern pattern = Pattern.compile(PASSWORD_REGEX);
        Matcher matcher = pattern.matcher(password);
        return matcher.matches();
    }

}
