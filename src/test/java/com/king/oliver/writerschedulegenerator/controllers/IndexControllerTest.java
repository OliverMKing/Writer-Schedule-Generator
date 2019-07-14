package com.king.oliver.writerschedulegenerator.controllers;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class IndexControllerTest {

    IndexController indexController;

    @Before
    public void setUp() {
        indexController = new IndexController();
    }

    @Test
    public void getIndex() {

        String templateName = indexController.getIndex();
        assertEquals("index", templateName);
    }
}