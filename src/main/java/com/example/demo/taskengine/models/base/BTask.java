package com.example.demo.taskengine.models.base;

import javax.persistence.Entity;

@Entity
public class BTask extends Task<BContext, BTask> {

    private Long another;


    public Long getAnother() {
        return another;
    }

    public BTask setAnother(Long another) {
        this.another = another;
        return this;
    }

    @Override
    public BContext getContext() {
        return context;
    }

    @Override
    public BTask setContext(BContext context) {
        this.context = context;
        return this;
    }

    @Override
    public void execute() {
        System.err
                .println(String.format("Executing task %s, data [%s]", this.getClass(), getContext().getData().getData()));
    }
}
