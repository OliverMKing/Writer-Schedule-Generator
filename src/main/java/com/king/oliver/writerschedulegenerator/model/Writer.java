package com.king.oliver.writerschedulegenerator.model;

import org.hibernate.validator.constraints.URL;

import javax.persistence.*;

@Entity
@Table(name = "writers")
public class Writer extends Named {

    @Column(name = "url")
    @URL(message = "Url must be valid (with protocol specified)")
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
