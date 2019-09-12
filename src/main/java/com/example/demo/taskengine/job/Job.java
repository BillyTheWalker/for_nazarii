package com.example.demo.taskengine.job;

import com.example.demo.taskengine.models.base.Task;

public interface Job<T extends Task> {
    void perform(T task);
}
