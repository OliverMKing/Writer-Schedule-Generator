package com.king.oliver.writerschedulegenerator.model;

import org.hibernate.annotations.SortNatural;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.*;

@Entity
@Table(name = "schedules")
public class Schedule extends Named {

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "schedule")
    @SortNatural
    private SortedSet<Slot> slots = new TreeSet<>();

    public Schedule() {
    }

    public Schedule(SortedSet<Slot> slots) {
        this.slots = slots;
    }

    public Schedule addSlot(Slot slot) {
        slot.setSchedule(this);
        slots.add(slot);
        return this;
    }

    public SortedSet<Slot> getSlots() {
        return slots;
    }

    public void setSlots(SortedSet<Slot> slots) {
        this.slots = slots;
    }
}
