package com.king.oliver.writerschedulegenerator.services.springjpa;

import com.king.oliver.writerschedulegenerator.model.Editor;
import com.king.oliver.writerschedulegenerator.model.Slot;
import com.king.oliver.writerschedulegenerator.model.Writer;
import com.king.oliver.writerschedulegenerator.repositories.SlotRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class SlotJpaServiceTest {

    @Mock
    SlotRepository slotRepository;

    @InjectMocks
    SlotJpaService slotJpaService;

    private Slot slot;

    @Before
    public void setUp() throws Exception {
        slot = new Slot(LocalDate.now(), new Writer("writer", "www.test.com"), new Editor("editor"));
        slot.setId(1L);
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void findAll() {
        Set<Slot> slots = new HashSet<>();
        slots.add(slot);
        slots.add(new Slot());

        when(slotRepository.findAll()).thenReturn(slots);

        Set<Slot> returnedSlots = slotJpaService.findAll();

        assertNotNull(returnedSlots);
        assertEquals(slots.size(), returnedSlots.size());
    }

    @Test
    public void findById() {
        when(slotRepository.findById(anyLong())).thenReturn(Optional.of(slot));
        Slot returnSlot = slotJpaService.findById(1L);
        assertNotNull(returnSlot);
    }

    @Test
    public void save() {
        when(slotRepository.save(slot)).thenReturn(slot);
        Slot returnSlot = slotJpaService.save(slot);
        assertNotNull(returnSlot);
        verify(slotRepository).save(any());
    }

    @Test
    public void delete() {
        slotJpaService.delete(slot);
        verify(slotRepository, times(1)).delete(any());
    }

    @Test
    public void deleteById() {
        slotJpaService.deleteById(1L);
        verify(slotRepository, times(1)).deleteById(any());
    }
}