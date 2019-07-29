package com.king.oliver.writerschedulegenerator.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

@Entity
@Table(name = "schedules")
public class Schedule extends Named {

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "schedule")
    private Set<Slot> slots = new TreeSet<>();

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
