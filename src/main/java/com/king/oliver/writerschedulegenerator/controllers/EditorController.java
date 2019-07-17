package com.king.oliver.writerschedulegenerator.controllers;

import com.king.oliver.writerschedulegenerator.repositories.EditorRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/editor")
public class EditorController {
    
    private final EditorRepository editorRepository;

    public EditorController(EditorRepository editorRepository) {
        this.editorRepository = editorRepository;
    }

    @RequestMapping({"", "/"})
    public String showEditors(Model model) {

        model.addAttribute("editors", editorRepository.findAll());
        return "editor/index";
    }
}
