package com.example.demo.taskengine.service;

import com.example.demo.DynamicScheduler;
import com.example.demo.taskengine.models.base.ScheduledTask;
import com.example.demo.taskengine.models.base.Task;
import com.example.demo.taskengine.models.base.TimedTask;
import com.example.demo.taskengine.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TaskService {

    @Autowired
    private DynamicScheduler dynamicScheduler;
    @Autowired
    private TaskRepository taskRepository;

    public Task saveTask(Task scheduledTask) {
        if (scheduledTask instanceof TimedTask) {
            return saveTask((ScheduledTask) scheduledTask, scheduledTask.isActive());
        } else {
            return saveTaskInternal(scheduledTask);
        }

    }

    private Task saveTaskInternal(Task task) {
        return taskRepository.save(task);
    }

    public TimedTask saveTask(TimedTask scheduledTask, boolean start) {
        scheduledTask = taskRepository.save(scheduledTask);
        if (start) {
            dynamicScheduler.addTask(scheduledTask);
        }
        return scheduledTask;
    }

    public TimedTask startTask(TimedTask scheduledTask) {
        scheduledTask =
                (TimedTask) taskRepository.findById(scheduledTask.getId()).orElse(taskRepository.save(scheduledTask));
        dynamicScheduler.addTask(scheduledTask);
        return scheduledTask;
    }
}
