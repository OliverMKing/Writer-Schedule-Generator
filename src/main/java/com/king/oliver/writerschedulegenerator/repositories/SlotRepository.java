package com.king.oliver.writerschedulegenerator.repositories;

import com.king.oliver.writerschedulegenerator.model.Slot;
import org.springframework.data.repository.CrudRepository;

public interface SlotRepository extends CrudRepository<Slot, Long> {
}
