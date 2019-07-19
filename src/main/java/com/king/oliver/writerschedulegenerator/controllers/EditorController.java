package com.king.oliver.writerschedulegenerator.controllers;

import com.king.oliver.writerschedulegenerator.repositories.EditorRepository;
import com.king.oliver.writerschedulegenerator.services.EditorService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/editor")
public class EditorController {
    
    private final EditorService editorService;

    public EditorController(EditorService editorService) {
        this.editorService = editorService;
    }

    @RequestMapping({"", "/"})
    public String showEditors(Model model) {

        model.addAttribute("editors", editorService.findAll());
        return "editor/index";
    }
}
