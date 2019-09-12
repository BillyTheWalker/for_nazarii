package com.example.demo.taskengine.models.base;

import javax.persistence.MappedSuperclass;
import java.util.Date;

@MappedSuperclass
public abstract class ScheduledTask<C extends Context, T extends ScheduledTask> extends TimedTask<C, T> {

    private String cronExpression;
    private Date previousExecutionTime;

    public String getCronExpression() {
        return cronExpression;
    }

    public ScheduledTask<C, T> setCronExpression(String cronExpression) {
        this.cronExpression = cronExpression;
        return this;
    }

    public Date getPreviousExecutionTime() {
        return previousExecutionTime;
    }

    public ScheduledTask<C, T> setPreviousExecutionTime(Date previousExecutionTime) {
        this.previousExecutionTime = previousExecutionTime;
        return this;
    }
}
