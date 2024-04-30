package controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class Test {

    @GetMapping("/login")
    public String index(){
        return "test.html";
    }
//    @GetMapping("/login")
//    public String login() {
//        return "login/index";
//    }
}
