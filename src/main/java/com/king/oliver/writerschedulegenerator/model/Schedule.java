package com.king.oliver.writerschedulegenerator.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "schedules")
public class Schedule extends Named {

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "schedule")
    private Set<Slot> slots = new HashSet<>();

    public Schedule() {
    }

    public Schedule(Set<Slot> slots) {
        this.slots = slots;
    }

    public Schedule addSlot(Slot slot) {
        slot.setSchedule(this);
        slots.add(slot);
        return this;
    }

    public Set<Slot> getSlots() {
        return slots;
    }

    public void setSlots(Set<Slot> slots) {
        this.slots = slots;
    }
}
