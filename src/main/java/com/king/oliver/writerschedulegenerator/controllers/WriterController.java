package com.king.oliver.writerschedulegenerator.controllers;

import com.king.oliver.writerschedulegenerator.services.WriterService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/writer")
public class WriterController {

    private final WriterService writerService;

    public WriterController(WriterService writerService) {
        this.writerService = writerService;
    }

    @RequestMapping({"", "/"})
    public ModelAndView listWriters() {

        ModelAndView mav = new ModelAndView("writer/index");
        mav.addObject("writers", writerService.findAll());
        return mav;
    }

    @GetMapping("/{writerId}")
    public ModelAndView showWriter(@PathVariable Long writerId) {

        ModelAndView mav = new ModelAndView("writer/show");
        mav.addObject("writer", writerService.findById(writerId));
        return mav;
    }
}
