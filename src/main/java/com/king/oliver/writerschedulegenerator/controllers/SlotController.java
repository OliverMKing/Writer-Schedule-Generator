package com.king.oliver.writerschedulegenerator.controllers;

import com.king.oliver.writerschedulegenerator.model.Schedule;
import com.king.oliver.writerschedulegenerator.model.Slot;
import com.king.oliver.writerschedulegenerator.services.EditorService;
import com.king.oliver.writerschedulegenerator.services.ScheduleService;
import com.king.oliver.writerschedulegenerator.services.SlotService;
import com.king.oliver.writerschedulegenerator.services.WriterService;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
public class SlotController {

    private final SlotService slotService;
    private final ScheduleService scheduleService;
    private final WriterService writerService;
    private final EditorService editorService;

    public SlotController(SlotService slotService, ScheduleService scheduleService, WriterService writerService, EditorService editorService) {
        this.slotService = slotService;
        this.scheduleService = scheduleService;
        this.writerService = writerService;
        this.editorService = editorService;
    }

    @GetMapping("/schedule/{scheduleId}/slot/{slotId}/delete")
    public String deleteSlot(@PathVariable Long scheduleId, @PathVariable Long slotId) {
        slotService.deleteById(slotId);
        return "redirect:/schedule/" + scheduleId;
    }

    @GetMapping("/schedule/{scheduleId}/slot/new")
    public ModelAndView initCreateSlot(@PathVariable Long scheduleId) {
        ModelAndView mav = new ModelAndView("slot/new");
        Schedule schedule = scheduleService.findById(scheduleId);
        Slot slot = new Slot();
        mav.addObject("slot", slot);
        mav.addObject("schedule", schedule);
        mav.addObject("writers", writerService.findAll());
        mav.addObject("editors", editorService.findAll());
        return mav;
    }

    @PostMapping("/schedule/{scheduleId}/slot/new")
    public ModelAndView postCreateSlot(@Valid Slot slot, BindingResult result, @PathVariable Long scheduleId) {
        if (result.hasErrors()) {
            Schedule schedule = scheduleService.findById(scheduleId);
            ModelAndView mav = new ModelAndView("slot/new");
            mav.addObject("slot", slot);
            mav.addObject("schedule", schedule);
            mav.addObject("writers", writerService.findAll());
            mav.addObject("editors", editorService.findAll());
            return mav;
        } else {
            Schedule schedule = scheduleService.findById(scheduleId);
            schedule.addSlot(slot);
            Schedule saved = scheduleService.save(schedule);
            return new ModelAndView("redirect:/schedule/" + saved.getId());
        }
    }

    @GetMapping("/schedule/{scheduleId}/slot/{slotId}/update")
    public ModelAndView initUpdateSlot(@PathVariable Long scheduleId, @PathVariable Long slotId) {
        Schedule schedule = scheduleService.findById(scheduleId);
        ModelAndView mav = new ModelAndView("slot/update");
        mav.addObject("slot", slotService.findById(slotId));
        mav.addObject("schedule", schedule);
        mav.addObject("writers", writerService.findAll());
        mav.addObject("editors", editorService.findAll());
        return mav;
    }

    @PostMapping("/schedule/{scheduleId}/slot/{slotId}/update")
    public ModelAndView postUpdateSlot(@Valid Slot slot, BindingResult result, @PathVariable Long scheduleId, @PathVariable Long slotId) {
        if (result.hasErrors()) {
            Schedule schedule = scheduleService.findById(scheduleId);
            ModelAndView mav = new ModelAndView("slot/update");
            mav.addObject("slot", slot);
            mav.addObject("schedule", schedule);
            mav.addObject("writers", writerService.findAll());
            mav.addObject("editors", editorService.findAll());
            return mav;
        } else {
            Schedule schedule = scheduleService.findById(scheduleId);
            for (Slot currSlot : schedule.getSlots()) {
                if (currSlot.getId() == slotId) {
                    currSlot.setDate(slot.getDate());
                    currSlot.setWriter(slot.getWriter());
                    currSlot.setEditor(slot.getEditor());
                }
            }
            Schedule saved = scheduleService.save(schedule);
            return new ModelAndView("redirect:/schedule/" + saved.getId());
        }
    }
}
