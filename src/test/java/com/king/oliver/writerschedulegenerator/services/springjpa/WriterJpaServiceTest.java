package com.king.oliver.writerschedulegenerator.services.springjpa;

import com.king.oliver.writerschedulegenerator.model.Writer;
import com.king.oliver.writerschedulegenerator.repositories.WriterRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class WriterJpaServiceTest {

    @Mock
    WriterRepository writerRepository;

    @InjectMocks
    WriterJpaService writerJpaService;

    private Writer writer;

    @Before
    public void setUp() throws Exception {
        writer = new Writer("Test Writer", "www.test.com");
        writer.setId(1L);
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void findAll() {
        Set<Writer> writers = new HashSet<>();
        writers.add(new Writer("Writer 1", "www.url1.com"));
        writers.add(new Writer("Writer 2", "www.url2.com"));

        when(writerRepository.findAll()).thenReturn(writers);

        Set<Writer> returnedWriters = writerJpaService.findAll();

        assertNotNull(returnedWriters);
        assertEquals(writers.size(), returnedWriters.size());
    }

    @Test
    public void findById() {
        when(writerRepository.findById(anyLong())).thenReturn(Optional.of(writer));
        Writer returnWriter = writerJpaService.findById(1L);
        assertNotNull(returnWriter);
    }

    @Test
    public void save() {
        when(writerRepository.save(writer)).thenReturn(writer);
        Writer returnWriter = writerJpaService.save(writer);
        assertNotNull(returnWriter);
        verify(writerRepository).save(any());
    }

    @Test
    public void delete() {
        writerJpaService.delete(writer);
        verify(writerRepository, times(1)).delete(any());
    }

    @Test
    public void deleteById() {
        writerJpaService.deleteById(1L);
        verify(writerRepository, times(1)).deleteById(anyLong());
    }

    @Test
    public void findByName() {
        when(writerRepository.findByName(anyString())).thenReturn(writer);
        Writer returnWriter = writerJpaService.findByName("Test Writer");
        assertEquals("Test Writer", returnWriter.getName());
        verify(writerRepository).findByName(any());
    }
}