package com.king.oliver.writerschedulegenerator.controllers;

import com.king.oliver.writerschedulegenerator.model.Editor;
import com.king.oliver.writerschedulegenerator.services.EditorService;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.Model;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
public class EditorControllerTest {

    @Mock
    EditorService editorService;

    @InjectMocks
    EditorController editorController;

    MockMvc mockMvc;

    Set<Editor> editors;


    @Before
    public void setUp(   ) throws Exception {

        editors = new HashSet<>();
        Editor oliver = new Editor("Oliver King");
        editors.add(oliver);
        Editor sam = new Editor("Sam VerNooy");
        editors.add(sam);

        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders
                .standaloneSetup(editorController)
                .build();
    }

    @Test
    public void showEditors() throws Exception {

        when(editorService.findAll()).thenReturn(editors);

        mockMvc.perform(get("/editor"))
                .andExpect(status().isOk())
                .andExpect(view().name("editor/index"))
                .andExpect(model().attributeExists("editors"));
    }
}