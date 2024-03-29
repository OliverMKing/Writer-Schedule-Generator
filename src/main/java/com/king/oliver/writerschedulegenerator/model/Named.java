package com.king.oliver.writerschedulegenerator.model;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotBlank;

@MappedSuperclass
public class Named extends BaseEntity {

    @Column(name = "name")
    @NotBlank(message = "Name cannot be blank")
    private String name;

    public Named() {
    }

    public Named(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
