package com.example.project3.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.example.project3.models.Member;
import com.example.project3.models.ThietBi;
import com.example.project3.service.MemberService;
import jakarta.servlet.http.HttpSession;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ThietBiController {

    @Autowired
    private MemberService memberService;

    @GetMapping("/thietbi")
    public List<ThietBi> getAllThietBi() {
        return memberService.getAllThietBi();
    }
}
