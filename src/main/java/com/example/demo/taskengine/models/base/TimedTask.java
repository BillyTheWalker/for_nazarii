package com.example.demo.taskengine.models.base;

import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class TimedTask<C extends Context, T extends TimedTask> extends Task<C, T> {

}
