package com.king.oliver.writerschedulegenerator.services;

import com.king.oliver.writerschedulegenerator.model.Schedule;

public interface ScheduleService extends CrudService<Schedule, Long> {

    Schedule findByName(String name);
}
