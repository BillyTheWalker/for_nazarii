package com.example.demo.taskengine.models.base;

import javax.persistence.Entity;
import java.util.Date;

@Entity
public class AScheduledTask extends ScheduledTask<AContext, AScheduledTask> {

    @Override
    public void execute() {
        System.err.println(String.format("Executing cron task [%s], date [%s]", this.getClass(), new Date()));
    }
}
