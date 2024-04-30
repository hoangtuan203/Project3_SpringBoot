package com.example.project3.controller;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class test {
    @RequestMapping("/login")
    public String login(Model model) {
        
        return "test";
    }
}
