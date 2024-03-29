package com.king.oliver.writerschedulegenerator.controllers;

import com.king.oliver.writerschedulegenerator.model.Editor;
import com.king.oliver.writerschedulegenerator.services.EditorService;
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

import java.util.HashSet;
import java.util.Set;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
public class EditorControllerTest {

    @Mock
    EditorService editorService;

    @InjectMocks
    EditorController editorController;

    private MockMvc mockMvc;

    private Set<Editor> editors;

    @Before
    public void setUp() throws Exception {

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
                .andExpect(model().attribute("editors", equalTo(editors)));
    }

    @Test
    public void deleteEditor() throws Exception {
        mockMvc.perform(get("/editor/1/delete"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/editor"));

        verify(editorService, times(1)).deleteById(anyLong());
    }

    @Test
    public void initCreateEditor() throws Exception{
        mockMvc.perform(get("/editor/new"))
                .andExpect(status().isOk())
                .andExpect(view().name("editor/new"))
                .andExpect(model().attributeExists("editor"));
    }

    @Test
    public void postCreateEditor() throws Exception {
        Editor editor = new Editor();
        editor.setId(1L);
        when(editorService.save(any())).thenReturn(editor);

        mockMvc.perform(post("/editor/new")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("id", "")
                .param("name", "Test"))
                .andExpect(status().is3xxRedirection());
    }

    @Test
    public void initUpdateEditor() throws Exception {
        Editor editor = new Editor();
        when(editorService.findById(anyLong())).thenReturn(editor);

        mockMvc.perform(get("/editor/1/update"))
                .andExpect(status().isOk())
                .andExpect(view().name("editor/update"))
                .andExpect(model().attributeExists("editor"));
    }

    @Test
    public void postUpdateEditor() throws Exception {
        Editor editor = new Editor();
        editor.setId(1L);
        when(editorService.save(any())).thenReturn(editor);

        mockMvc.perform(post("/editor/1/update")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("id", "")
                .param("name", "New"))
                .andExpect(status().is3xxRedirection());
    }
}