package com.project.permission.control;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class TestControl {

    @RequestMapping("/test")
    public String test(){
        return "hello";
    }
}
