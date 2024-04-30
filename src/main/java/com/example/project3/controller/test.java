package com.example.project3.controller;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class test {
    @RequestMapping("/login")
    public String login() {
        
        return "test";
    }
    @RequestMapping("/index")
    public String index() {
        
        return "test";
    }
}
