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
    @PostMapping("/register")
    public String register(@RequestParam int maTV, @RequestParam String email, @RequestParam String password, @RequestParam String confirmPassword, Model model) {
        if (isValidEmail(email) && isValidPassword(password) && password.equals(confirmPassword)) {
            if (registerService.existsByMaTV(maTV)) {
                Member existingMember = registerService.findMemberByMaTV(maTV);
                existingMember.setEmail(email);
                existingMember.setPassword(password);
                registerService.saveMember(existingMember);
                model.addAttribute("member", existingMember);
                return "login"; 
            } else {
                Member newMember = new Member();
                newMember.setMaTV(maTV);
                newMember.setEmail(email);
                newMember.setPassword(password);
                registerService.saveMember(newMember);
                model.addAttribute("member", newMember);
                return "login"; 
            }
        } else {
            model.addAttribute("error", "Email hoặc mật khẩu không hợp lệ hoặc mật khẩu không khớp.");
            return "register"; 
        }
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
