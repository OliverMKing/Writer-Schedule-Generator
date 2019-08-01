package com.king.oliver.writerschedulegenerator.controllers;

import com.king.oliver.writerschedulegenerator.model.Editor;
import com.king.oliver.writerschedulegenerator.model.Slot;
import com.king.oliver.writerschedulegenerator.model.Writer;
import com.king.oliver.writerschedulegenerator.services.EditorService;
import com.king.oliver.writerschedulegenerator.services.ScheduleService;
import com.king.oliver.writerschedulegenerator.services.SlotService;
import com.king.oliver.writerschedulegenerator.services.WriterService;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.LocalDate;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
public class SlotControllerTest {

    @Mock
    SlotService slotService;

    @Mock
    ScheduleService scheduleService;

    @Mock
    WriterService writerService;

    @Mock
    EditorService editorService;

    @InjectMocks
    SlotController slotController;

    private MockMvc mockMvc;
    private Slot slot;

    @Before
    public void setUp() throws Exception {
        slot = new Slot(LocalDate.now(), new Writer("writer", "www.test.com"), new Editor("editor"));
        slot.setId(1L);

        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders
                .standaloneSetup(slotController)
                .build();
    }

    @Test
    public void deleteSlot() throws Exception {
        mockMvc.perform(get("/schedule/1/slot/1/delete"))
            .andExpect(status().is3xxRedirection())
            .andExpect(view().name("redirect:/schedule/1"));

        verify(slotService, times(1)).deleteById(anyLong());
    }

    @Test
    public void initCreateSlot() throws Exception {
        mockMvc.perform(get("/schedule/1/slot/new"))
                .andExpect(status().isOk())
                .andExpect(view().name("slot/new"))
                .andExpect(model().attributeExists("slot"))
                .andExpect(model().attributeExists("writers"))
                .andExpect(model().attributeExists("editors"));
    }

    @Test
    public void initUpdateSlot() throws Exception {
        mockMvc.perform(get("/schedule/1/slot/1/update"))
                .andExpect(status().isOk())
                .andExpect(view().name("slot/update"))
                .andExpect(model().attributeExists("writers"))
                .andExpect(model().attributeExists("editors"));
    }

}