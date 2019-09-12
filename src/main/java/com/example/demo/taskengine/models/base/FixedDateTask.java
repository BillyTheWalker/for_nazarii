package com.example.demo.taskengine.models.base;

import javax.persistence.MappedSuperclass;
import java.util.Date;

@MappedSuperclass
public abstract class FixedDateTask<C extends Context, T extends FixedDateTask> extends TimedTask<C, T> {

    private Date executionTime;

    public Date getExecutionTime() {
        return executionTime;
    }

    public FixedDateTask<C, T> setExecutionTime(Date executionTime) {
        this.executionTime = executionTime;
        return this;
    }
}
