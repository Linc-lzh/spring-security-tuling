package com.luban.fox.security01.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Fox
 */
@Controller
public class LoginController {

    @RequestMapping("/tomain")
    public String tomain(){
        return "redirect:/main.html";
    }

    @RequestMapping("/showLogin")
    public String showLogin(){
        return "login";
    }
}
