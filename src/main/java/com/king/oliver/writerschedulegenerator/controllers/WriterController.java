package com.king.oliver.writerschedulegenerator.controllers;

import com.king.oliver.writerschedulegenerator.services.WriterService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/writer")
public class WriterController {

    private final WriterService writerService;

    public WriterController(WriterService writerService) {
        this.writerService = writerService;
    }

    @RequestMapping({"", "/"})
    public String listWriters(Model model) {

        model.addAttribute("writers", writerService.findAll());
        return "writer/index";
    }
}
