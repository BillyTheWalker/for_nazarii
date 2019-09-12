package com.example.demo.taskengine.models.base;

import javax.persistence.Entity;

@Entity
public class ATask extends Task<AContext, ATask> {

    private String some;

    public String getSome() {
        return some;
    }

    public ATask setSome(String some) {
        this.some = some;
        return this;
    }

    @Override
    public AContext getContext() {
        return context;
    }

    @Override
    public ATask setContext(AContext context) {
        this.context = context;
        return this;
    }

    @Override
    public void execute() {
        System.err.println(String.format("Executing task %s, data [%s]", this.getClass(), getContext().getData()));
    }
}
