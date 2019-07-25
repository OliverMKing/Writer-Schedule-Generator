package com.king.oliver.writerschedulegenerator.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDate;

@Entity
@Table(name = "slots")
public class Slot extends BaseEntity {

    @Column(name = "date")
    private LocalDate date;

    @Column(name = "writer")
    private Writer writer;

    @Column(name = "editor")
    private Editor editor;

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
}
