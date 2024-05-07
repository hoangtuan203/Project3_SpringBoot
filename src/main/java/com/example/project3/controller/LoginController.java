package com.example.project3.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.project3.models.Member;

import com.example.project3.service.MemberService;


@Controller
public class LoginController {
     private MemberService memberService;

     public LoginController(MemberService memberService) {
        this.memberService = memberService;
    }
    @PostMapping("/login")
    public String loginMember(@RequestParam int maTV, @RequestParam String password, RedirectAttributes redirectAttributes) {
        Member member = memberService.loginMember(maTV, password);
        if (member != null) {
            redirectAttributes.addFlashAttribute("member", member);

            return "redirect:/index"; 
        } else {
            
            return "redirect:/login?error"; // Chuyển hướng đến trang đăng nhập và hiển thị thông báo lỗi
        }
    }
    @GetMapping("/index")
    public String indexAfterLogin(@ModelAttribute("member") Member member, Model model) {
        model.addAttribute("member", member);
        return "index";
    }
}