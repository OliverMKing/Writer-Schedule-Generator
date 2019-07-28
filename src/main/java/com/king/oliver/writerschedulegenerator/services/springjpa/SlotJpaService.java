package com.king.oliver.writerschedulegenerator.services.springjpa;

import com.king.oliver.writerschedulegenerator.model.Slot;
import com.king.oliver.writerschedulegenerator.repositories.SlotRepository;
import com.king.oliver.writerschedulegenerator.services.SlotService;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class SlotJpaService implements SlotService {

    private final SlotRepository slotRepository;

    public SlotJpaService(SlotRepository slotRepository) {
        this.slotRepository = slotRepository;
    }

    @Override
    public Set<Slot> findAll() {

        Set<Slot> slots = new HashSet<>();
        slotRepository.findAll().forEach(slots::add);
        return slots;
    }

    @Override
    public Slot findById(Long aLong) {
        return slotRepository.findById(aLong).orElse(null);
    }

    @Override
    public Slot save(Slot object) {
        return slotRepository.save(object);
    }

    @Override
    public void delete(Slot object) {
        slotRepository.delete(object);
    }

    @Override
    public void deleteById(Long aLong) {
        slotRepository.deleteById(aLong);
    }
}
