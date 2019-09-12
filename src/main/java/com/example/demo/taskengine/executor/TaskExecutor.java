package com.example.demo.taskengine.executor;

import com.example.demo.taskengine.models.base.Task;

public interface TaskExecutor {
    <T extends Task> void execute(T task);
}
