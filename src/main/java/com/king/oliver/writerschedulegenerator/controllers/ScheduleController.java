package com.king.oliver.writerschedulegenerator.controllers;

import com.king.oliver.writerschedulegenerator.model.Schedule;
import com.king.oliver.writerschedulegenerator.services.ScheduleService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/schedule")
public class ScheduleController {

    private final ScheduleService scheduleService;

    public ScheduleController(ScheduleService scheduleService) {
        this.scheduleService = scheduleService;
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

    @GetMapping("/new")
    public ModelAndView initCreateSchedule() {
        ModelAndView mav = new ModelAndView("schedule/new");
        mav.addObject("schedule", new Schedule());
        return mav;
    }
}
