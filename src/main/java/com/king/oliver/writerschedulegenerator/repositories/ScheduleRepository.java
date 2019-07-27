package com.king.oliver.writerschedulegenerator.repositories;

import com.king.oliver.writerschedulegenerator.model.Schedule;
import org.springframework.data.repository.CrudRepository;

public interface ScheduleRepository extends CrudRepository<Schedule, Long> {

    Schedule findByName(String name);
}
