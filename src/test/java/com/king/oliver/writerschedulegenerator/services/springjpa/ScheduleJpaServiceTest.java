package com.king.oliver.writerschedulegenerator.services.springjpa;

import com.king.oliver.writerschedulegenerator.model.Editor;
import com.king.oliver.writerschedulegenerator.model.Schedule;
import com.king.oliver.writerschedulegenerator.model.Slot;
import com.king.oliver.writerschedulegenerator.model.Writer;
import com.king.oliver.writerschedulegenerator.repositories.ScheduleRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.*;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ScheduleJpaServiceTest {

    @Mock
    ScheduleRepository scheduleRepository;

    @InjectMocks
    ScheduleJpaService scheduleJpaService;

    private Schedule schedule;

    @Before
    public void setUp() throws Exception {
        Slot slot = new Slot(LocalDate.now(), new Writer("writer", "www.test.com"), new Editor("editor"));
        Slot slot2 = new Slot(LocalDate.now(), new Writer("writer2", "www.test2.com"), new Editor("editor2"));
        TreeSet<Slot> slots = new TreeSet<>();
        slots.add(slot);
        slots.add(slot2);
        schedule = new Schedule(slots);
        schedule.setName("Test Schedule");
        schedule.setId(1L);
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void findByName() {
        when(scheduleRepository.findByName(anyString())).thenReturn(schedule);
        Schedule returnSchedule = scheduleJpaService.findByName("Test Schedule");
        assertEquals("Test Schedule", returnSchedule.getName());
        verify(scheduleRepository).findByName(any());
    }

    @Test
    public void findAll() {
        Set<Schedule> schedules = new HashSet<>();
        schedules.add(schedule);
        schedules.add(new Schedule());

        when(scheduleRepository.findAll()).thenReturn(schedules);
        Set<Schedule> returnedSchedules = scheduleJpaService.findAll();

        assertNotNull(returnedSchedules);
        assertEquals(schedules.size(), returnedSchedules.size());
    }

    @Test
    public void findById() {
        when(scheduleRepository.findById(anyLong())).thenReturn(Optional.of(schedule));
        Schedule returnedSchedule = scheduleJpaService.findById(1L);
        assertNotNull(returnedSchedule);
    }

    @Test
    public void save() {
        when(scheduleRepository.save(schedule)).thenReturn(schedule);
        Schedule returnedSchedule = scheduleJpaService.save(schedule);
        assertNotNull(returnedSchedule);
        verify(scheduleRepository).save(any());
    }

    @Test
    public void delete() {
        scheduleJpaService.delete(schedule);
        verify(scheduleRepository, times(1)).delete(any());
    }

    @Test
    public void deleteById() {
        scheduleJpaService.deleteById(1L);
        verify(scheduleRepository, times(1)).deleteById(anyLong());
    }
}