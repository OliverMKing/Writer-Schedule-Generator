package com.king.oliver.writerschedulegenerator.controllers;

import com.king.oliver.writerschedulegenerator.model.Editor;
import com.king.oliver.writerschedulegenerator.services.EditorService;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

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

    @InitBinder
    public void setAllowedFields(WebDataBinder dataBinder) {
        dataBinder.setDisallowedFields("id");
    }

    @GetMapping("/new")
    public ModelAndView initCreateEditor() {
        ModelAndView mav = new ModelAndView("editor/new");
        mav.addObject(new Editor());
        return mav;
    }

    @PostMapping("/new")
    public ModelAndView postCreateEditor(@Valid Editor editor, BindingResult result) {
        if (result.hasErrors()) {
            return new ModelAndView("editor/new");
        } else {
            Editor saved = editorService.save(editor);
            return new ModelAndView("redirect:");
        }
    }
}
