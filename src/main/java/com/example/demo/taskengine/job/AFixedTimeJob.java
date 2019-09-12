package com.example.demo.taskengine.job;

import com.example.demo.taskengine.models.base.AFixedDateTask;
import com.example.demo.taskengine.repository.TaskRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AFixedTimeJob implements Job<AFixedDateTask> {

    private static final Logger LOGGER = LoggerFactory.getLogger(AFixedTimeJob.class);

    @Autowired
    private TaskRepository taskRepository;

    @Override
    public void perform(AFixedDateTask task) {
        LOGGER.info(task.getContext().getData());
        taskRepository.save(task.setActive(false));
    }
}
