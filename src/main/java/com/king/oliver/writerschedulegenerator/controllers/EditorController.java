package com.king.oliver.writerschedulegenerator.controllers;

import com.king.oliver.writerschedulegenerator.services.EditorService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/editor")
public class EditorController {
    
    private final EditorService editorService;

    public EditorController(EditorService editorService) {
        this.editorService = editorService;
    }

    @RequestMapping({"", "/"})
    public ModelAndView showEditors() {

        ModelAndView mav = new ModelAndView("editor/index");
        mav.addObject("editors", editorService.findAll());
        return mav;
    }

    @GetMapping("/{editorId}/delete")
    public String deleteEditor(@PathVariable Long editorId) {

        editorService.deleteById(editorId);
        return "redirect:/editor";
    }
}
