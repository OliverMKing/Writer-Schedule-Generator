package com.king.oliver.writerschedulegenerator.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {

    @RequestMapping({"", "/"})
    public String getIndex() {
        return "index";
    }
}
