package com.example.demo.taskengine.job;

import com.example.demo.taskengine.models.base.AScheduledTask;
import com.example.demo.taskengine.repository.TaskRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class AScheduledJob implements Job<AScheduledTask> {

    private static final Logger LOGGER = LoggerFactory.getLogger(AScheduledJob.class);

    @Autowired
    private TaskRepository taskRepository;

    public void perform(AScheduledTask aScheduledTask) {
        LOGGER.info(aScheduledTask.getContext().getData());
        taskRepository.save(aScheduledTask.setPreviousExecutionTime(new Date()));
    }

}
