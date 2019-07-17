package com.king.oliver.writerschedulegenerator.model;

import javax.persistence.*;

@Entity
@Table(name = "writers")
public class Writer extends Person{

    @Column(name = "url")
    private String url;

    public Writer() {
    }

    public Writer(String name, String url) {
        super(name);
        this.url = url;
    }

    @Override
    public String toString() {
        return getName();
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

}
