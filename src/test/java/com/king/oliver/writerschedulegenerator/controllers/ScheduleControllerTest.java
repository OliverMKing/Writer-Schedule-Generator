package com.king.oliver.writerschedulegenerator.controllers;

import com.king.oliver.writerschedulegenerator.model.Editor;
import com.king.oliver.writerschedulegenerator.model.Schedule;
import com.king.oliver.writerschedulegenerator.model.Slot;
import com.king.oliver.writerschedulegenerator.model.Writer;
import com.king.oliver.writerschedulegenerator.services.EditorService;
import com.king.oliver.writerschedulegenerator.services.ScheduleService;
import com.king.oliver.writerschedulegenerator.services.WriterService;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
public class ScheduleControllerTest {

    @Mock
    ScheduleService scheduleService;

    @Mock
    WriterService writerService;

    @Mock
    EditorService editorService;

    @InjectMocks
    ScheduleController scheduleController;

    private MockMvc mockMvc;
    private Set<Schedule> schedules;
    private SortedSet<Slot> slots;
    private Schedule schedule;

    @Before
    public void setUp() throws Exception {
        slots = new TreeSet<>();
        slots.add(new Slot(LocalDate.now(), new Writer("writer", "www.test.com"), new Editor("editor")));
        slots.add(new Slot(LocalDate.now(), new Writer("writer2", "www.test2.com"), new Editor("editor2")));

        schedules = new HashSet<>();
        schedule = new Schedule(new TreeSet<>(), "schedule");
        schedules.add(schedule);
        schedules.add(new Schedule(new TreeSet<>(), "schedule2"));

        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders
                .standaloneSetup(scheduleController)
                .build();
    }

    @Test
    public void listSchedules() throws Exception {
        when(scheduleService.findAll()).thenReturn(schedules);

        mockMvc.perform(get("/schedule"))
                .andExpect(status().isOk())
                .andExpect(view().name("schedule/index"))
                .andExpect(model().attribute("schedules", equalTo(schedules)));
    }

    @Test
    public void showSchedule() throws Exception {
        when(scheduleService.findById(anyLong())).thenReturn(schedule);

        mockMvc.perform(get("/schedule/1"))
                .andExpect(status().isOk())
                .andExpect(view().name("schedule/show"))
                .andExpect(model().attribute("schedule", equalTo(schedule)));
    }

    @Test
    public void exportSchedule() throws Exception {
        when(scheduleService.findById(anyLong())).thenReturn(schedule);

        mockMvc.perform(get("/schedule/1/export"))
                .andExpect(status().isOk())
                .andExpect(view().name("schedule/export"))
                .andExpect(model().attribute("schedule", equalTo(schedule)));
    }

    @Test
    public void deleteSchedule() throws Exception {
        mockMvc.perform(get("/schedule/1/delete"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/schedule"));

        verify(scheduleService, times(1)).deleteById(anyLong());

    }

    @Test
    public void initCreateSchedule() throws Exception {
        mockMvc.perform(get("/schedule/new"))
                .andExpect(status().isOk())
                .andExpect(view().name("schedule/new"))
                .andExpect(model().attributeExists("schedule"));

    }


    @Test
    public void initUpdateSchedule() throws Exception {
        when(scheduleService.findById(anyLong())).thenReturn(schedule);

        mockMvc.perform(get("/schedule/1/update"))
                .andExpect(status().isOk())
                .andExpect(view().name("schedule/update"))
                .andExpect(model().attributeExists("schedule"));

    }

}