package com.king.oliver.writerschedulegenerator.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.Set;
import java.util.TreeSet;

@Entity
@Table(name = "schedules")
public class Schedule extends BaseEntity {

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "schedule")
    private Set<Slot> slots = new TreeSet<>();


    public Schedule() {
    }

    public Schedule(Set<Slot> slots) {
        this.slots = slots;
    }

    public Set<Slot> getSlots() {
        return slots;
    }

    public void setSlots(Set<Slot> slots) {
        this.slots = slots;
    }
}
