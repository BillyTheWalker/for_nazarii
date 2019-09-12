package com.example.demo.taskengine.models.base;

import javax.persistence.Entity;

@Entity
public class AContext extends Context {

    private String data;

    public String getData() {
        return data;
    }

    public AContext setData(String data) {
        this.data = data;
        return this;
    }
}
