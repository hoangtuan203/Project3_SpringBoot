package com.example.project3.controller;
import org.springframework.web.bind.annotation.GetMapping;
import com.example.project3.service.MemberService;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

@Controller
public class MemberController {
    private MemberService memberService;

    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping("/login")
    public String listMember(Model model) {
        model.addAttribute("members", memberService.getAllMember());
        return "test";         
    }
}
