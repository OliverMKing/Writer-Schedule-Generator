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
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.HashSet;
import java.util.Set;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
public class WriterControllerTest {

    @Mock
    WriterService writerService;

    @InjectMocks
    WriterController writerController;

    private MockMvc mockMvc;

    private Set<Writer> writers;

    @Before
    public void setUp() throws Exception {
        writers = new HashSet<>();
        Writer caleb = new Writer("Caleb Gedemer", "https://www.pokebeach.com/author/caleb-gedemer");
        writers.add(caleb);
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
}