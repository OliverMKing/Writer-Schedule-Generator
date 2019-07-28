package com.king.oliver.writerschedulegenerator.services.springjpa;

import com.king.oliver.writerschedulegenerator.model.Schedule;
import com.king.oliver.writerschedulegenerator.repositories.ScheduleRepository;
import com.king.oliver.writerschedulegenerator.services.ScheduleService;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class ScheduleJpaService implements ScheduleService {

    private final ScheduleRepository scheduleRepository;

    public ScheduleJpaService(ScheduleRepository scheduleRepository) {
        this.scheduleRepository = scheduleRepository;
    }

    @Override
    public Schedule findByName(String name) {
        return scheduleRepository.findByName(name);
    }

    @Override
    public Set<Schedule> findAll() {

        Set<Schedule> schedules = new HashSet<>();
        scheduleRepository.findAll().forEach(schedules::add);
        return schedules;
    }

    @Override
    public Schedule findById(Long aLong) {
        return scheduleRepository.findById(aLong).orElse(null);
    }

    @Override
    public Schedule save(Schedule object) {
        return scheduleRepository.save(object);
    }

    @Override
    public void delete(Schedule object) {
        scheduleRepository.delete(object);
    }

    @Override
    public void deleteById(Long aLong) {
        scheduleRepository.deleteById(aLong);
    }
}
