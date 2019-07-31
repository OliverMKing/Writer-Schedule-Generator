package com.king.oliver.writerschedulegenerator.controllers;

import com.king.oliver.writerschedulegenerator.model.Editor;
import com.king.oliver.writerschedulegenerator.model.Schedule;
import com.king.oliver.writerschedulegenerator.model.Slot;
import com.king.oliver.writerschedulegenerator.services.EditorService;
import com.king.oliver.writerschedulegenerator.services.ScheduleService;
import com.king.oliver.writerschedulegenerator.services.WriterService;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.time.LocalDate;

@Controller
@RequestMapping("/schedule")
public class ScheduleController {

    private final ScheduleService scheduleService;
    private final WriterService writerService;
    private final EditorService editorService;

    public ScheduleController(ScheduleService scheduleService, WriterService writerService, EditorService editorService) {
        this.scheduleService = scheduleService;
        this.writerService = writerService;
        this.editorService = editorService;
    }

    @RequestMapping({"", "/"})
    public ModelAndView listSchedules() {

        ModelAndView mav = new ModelAndView("schedule/index");
        mav.addObject("schedules", scheduleService.findAll());
        return mav;
    }

    @GetMapping("/{scheduleId}")
    public ModelAndView showSchedule(@PathVariable Long scheduleId) {
        ModelAndView mav = new ModelAndView("schedule/show");
        mav.addObject("schedule", scheduleService.findById(scheduleId));
        return mav;
    }

    @GetMapping("/{scheduleId}/export")
    public ModelAndView exportSchedule(@PathVariable Long scheduleId) {
        ModelAndView mav = new ModelAndView("schedule/export");
        mav.addObject("schedule", scheduleService.findById(scheduleId));
        return mav;
    }

    @GetMapping("/{scheduleId}/delete")
    public String deleteSchedule(@PathVariable Long scheduleId) {
        scheduleService.deleteById(scheduleId);
        return "redirect:/schedule";
    }

    @InitBinder
    public void setAllowedFields(WebDataBinder dataBinder) {
        dataBinder.setDisallowedFields("id");
    }

    @GetMapping("/new")
    public ModelAndView initCreateSchedule() {
        ModelAndView mav = new ModelAndView("schedule/new");
        Schedule schedule = new Schedule();
        mav.addObject("schedule", schedule);
        return mav;
    }

    @PostMapping("/new")
    public ModelAndView postCreateSchedule(@Valid Schedule schedule, BindingResult result) {
        if (result.hasErrors()) {
            ModelAndView mav = new ModelAndView("schedule/new");
            mav.addObject(schedule);
            return mav;
        } else {
            Schedule saved = scheduleService.save(schedule);
            return new ModelAndView("redirect:" + saved.getId());
        }
    }

    @GetMapping("/{scheduleId}/update")
    public ModelAndView initUpdateSchedule(@PathVariable Long scheduleId) {
        ModelAndView mav = new ModelAndView("schedule/update");
        mav.addObject("schedule", scheduleService.findById(scheduleId));
        return mav;
    }

    @PostMapping("/{scheduleId}/update")
    public ModelAndView postUpdateEditor(@Valid Schedule schedule, BindingResult result, @PathVariable Long scheduleId) {
        if (result.hasErrors()) {
            ModelAndView mav = new ModelAndView("schedule/update");
            schedule.setId(scheduleId);
            mav.addObject(schedule);
            return mav;
        } else {
            schedule.setId(scheduleId);
            Schedule saved = scheduleService.save(schedule);
            return new ModelAndView("redirect:/schedule");
        }
    }
}
