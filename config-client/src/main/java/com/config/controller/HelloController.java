package com.config.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Description:
 */
@Controller
public class HelloController {
    @RequestMapping("/test")
    public String hi(Model model){
        model.addAttribute("test","hello");
        return "hello";
    }
}
