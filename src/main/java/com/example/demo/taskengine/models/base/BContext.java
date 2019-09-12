package com.example.demo.taskengine.models.base;

import javax.persistence.Entity;
import javax.persistence.OneToOne;

@Entity
public class BContext extends Context {
    @OneToOne
    private AContext data;

    public AContext getData() {
        return data;
    }

    public BContext setData(AContext data) {
        this.data = data;
        return this;
    }
}
