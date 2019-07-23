package com.king.oliver.writerschedulegenerator.controllers;

import com.king.oliver.writerschedulegenerator.model.Writer;
import com.king.oliver.writerschedulegenerator.services.WriterService;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

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

    @GetMapping("/{writerId}/delete")
    public String deleteWriter(@PathVariable Long writerId) {
        writerService.deleteById(writerId);
        return "redirect:/writer";
    }

    @InitBinder
    public void setAllowedFields(WebDataBinder dataBinder) {
        dataBinder.setDisallowedFields("id");
    }

    @GetMapping("/new")
    public ModelAndView initCreateWriter() {
        ModelAndView mav = new ModelAndView("writer/new");
        mav.addObject("writer", new Writer());
        return mav;
    }

    @PostMapping("/new")
    public ModelAndView postCreateWriter(@Valid Writer writer, BindingResult result) {
        if (result.hasErrors()) {
            return new ModelAndView("writer/new");
        } else {
            Writer saved = writerService.save(writer);
            return new ModelAndView("redirect:" + saved.getId());
        }
    }

    @GetMapping("/{writerId}/update")
    public ModelAndView initUpdateWriter(@PathVariable Long writerId) {
        ModelAndView mav = new ModelAndView("writer/update");
        mav.addObject("writer", writerService.findById(writerId));
        return mav;
    }

    @PostMapping("/{writerId}/update")
    public ModelAndView postUpdateWriter(@Valid Writer writer, BindingResult result, @PathVariable Long writerId) {
        if (result.hasErrors()) {
            ModelAndView mav = new ModelAndView("writer/update");
            writer.setId(writerId);
            mav.addObject(writer);
            return mav;
        } else {
            writer.setId(writerId);
            Writer saved = writerService.save(writer);
            return new ModelAndView("redirect:");
        }
    }
}
