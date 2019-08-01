package com.king.oliver.writerschedulegenerator.controllers;

import com.king.oliver.writerschedulegenerator.model.Writer;
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

import java.util.HashSet;
import java.util.Set;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
public class WriterControllerTest {

    @Mock
    WriterService writerService;

    @InjectMocks
    WriterController writerController;

    private MockMvc mockMvc;
    private Set<Writer> writers;
    private Writer writer;

    @Before
    public void setUp() throws Exception {
        writers = new HashSet<>();
        Writer caleb = new Writer("Caleb Gedemer", "https://www.pokebeach.com/author/caleb-gedemer");
        writers.add(caleb);
        writer = caleb;
        Writer grant = new Writer("Grant Manley", "https://www.pokebeach.com/author/grant-manley");
        writers.add(grant);
        Writer stephane = new Writer("Stephane Ivanoff", "https://www.pokebeach.com/forums/members/lubyllule.117481/");
        writers.add(stephane);

        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders
                .standaloneSetup(writerController)
                .build();
    }

    @Test
    public void listWriters() throws Exception {
        when(writerService.findAll()).thenReturn(writers);

        mockMvc.perform(get("/writer"))
                .andExpect(status().isOk())
                .andExpect(view().name("writer/index"))
                .andExpect(model().attribute("writers", equalTo(writers)));
    }

    @Test
    public void showWriter() throws Exception {
        when(writerService.findById(anyLong())).thenReturn(writer);

        mockMvc.perform(get("/writer/1"))
                .andExpect(status().isOk())
                .andExpect(view().name("writer/show"))
                .andExpect(model().attribute("writer", equalTo(writer)));
    }

    @Test
    public void deleteWriter() throws Exception {
        mockMvc.perform(get("/writer/1/delete"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/writer"));

        verify(writerService, times(1)).deleteById(anyLong());
    }

    @Test
    public void initCreateWriter() throws Exception{
        mockMvc.perform(get("/writer/new"))
                .andExpect(status().isOk())
                .andExpect(view().name("writer/new"))
                .andExpect(model().attributeExists("writer"));
    }

    @Test
    public void postCreateWriter() throws Exception {
        writer.setId(1L);
        when(writerService.save(any())).thenReturn(writer);

        mockMvc.perform(post("/writer/new")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("id", "")
                .param("name", "Test"))
                .andExpect(status().is3xxRedirection());
    }

    @Test
    public void initUpdateWriter() throws Exception {

        Writer writer = new Writer();
        when(writerService.findById(anyLong())).thenReturn(writer);

        mockMvc.perform(get("/writer/1/update"))
                .andExpect(status().isOk())
                .andExpect(view().name("writer/update"))
                .andExpect(model().attributeExists("writer"));
    }

    @Test
    public void postUpdateWriter() throws Exception {
        writer.setId(1L);
        when(writerService.save(any())).thenReturn(writer);

        mockMvc.perform(post("/writer/1/update")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("id", "")
                .param("name", "New"))
                .andExpect(status().is3xxRedirection());
    }
}