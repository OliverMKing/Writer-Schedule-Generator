package com.king.oliver.writerschedulegenerator.model;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "slots")
public class Slot extends BaseEntity implements Comparable<Slot> {

    @Column(name = "date")
    private LocalDate date;

    @OneToOne
    private Writer writer;

    @OneToOne
    private Editor editor;

    @ManyToOne
    @JoinColumn(name = "schedule_id")
    private Schedule schedule;

    public Slot() {
    }

    public Slot(LocalDate date, Writer writer, Editor editor) {
        this.date = date;
        this.writer = writer;
        this.editor = editor;
    }

    @Override
    public String toString() {
        return "Slot{" +
                "date=" + date +
                ", writer=" + writer +
                ", editor=" + editor +
                '}';
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Writer getWriter() {
        return writer;
    }

    public void setWriter(Writer writer) {
        this.writer = writer;
    }

    public Editor getEditor() {
        return editor;
    }

    public void setEditor(Editor editor) {
        this.editor = editor;
    }

    public Schedule getSchedule() {
        return schedule;
    }

    public void setSchedule(Schedule schedule) {
        this.schedule = schedule;
    }

    @Override
    public int compareTo(Slot o) {
        return date.compareTo(o.getDate());
    }
}
