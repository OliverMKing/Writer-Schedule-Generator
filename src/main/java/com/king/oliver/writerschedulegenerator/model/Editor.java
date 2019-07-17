package com.king.oliver.writerschedulegenerator.model;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "editors")
public class Editor extends Person {

    public Editor() {
    }

    public Editor(String name) {
        super(name);
    }

    @Override
    public String toString() {
        return getName();
    }
}
