package com.example.demo;

import com.example.demo.taskengine.job.Job;
import com.example.demo.taskengine.models.base.*;
import com.example.demo.taskengine.repository.TimedTaskRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.ScheduledFuture;

@Service
public class DynamicScheduler implements SchedulingConfigurer {

    private static Logger LOGGER = LoggerFactory.getLogger(DynamicScheduler.class);

    private ScheduledTaskRegistrar scheduledTaskRegistrar;
    private Map<Long, ScheduledFuture> scheduledFutureMap = new HashMap<>();
    @Autowired
    private TimedTaskRepository timedTaskRepository;
    @Autowired
    private ApplicationContext applicationContext;

    @Bean
    public TaskScheduler poolScheduler() {
        ThreadPoolTaskScheduler scheduler = new ThreadPoolTaskScheduler();
        scheduler.setThreadNamePrefix("ThreadPoolTaskScheduler");
        scheduler.setPoolSize(1);
        scheduler.initialize();
        return scheduler;
    }

    private void activateTasks() {
        List<TimedTask> timedTasks = timedTaskRepository.findAll(Example.of((TimedTask) new AScheduledTask().setActive(true), ExampleMatcher.matchingAny()));
        if (timedTasks == null)
            timedTasks = new ArrayList<>();
        timedTasks.addAll(timedTaskRepository.findAll(Example.of((TimedTask) new AFixedDateTask().setActive(true), ExampleMatcher.matchingAny())));
        LOGGER.info(String.valueOf(timedTasks.size()));
        timedTasks.forEach(t -> {
            LOGGER.info(t.getId().toString());
            addTask(t);
        });
    }

    @Override
    public void configureTasks(ScheduledTaskRegistrar scheduledTaskRegistrar) {
        if (this.scheduledTaskRegistrar == null) {
            this.scheduledTaskRegistrar = scheduledTaskRegistrar;
        }
        if (this.scheduledTaskRegistrar.getScheduler() == null) {
            this.scheduledTaskRegistrar.setScheduler(poolScheduler());
        }
        activateTasks();
    }

    public void addTask(TimedTask scheduledTask) {
        if (scheduledFutureMap.containsKey(scheduledTask.getId())) {
            updateTask(scheduledTask);
        } else {
            if (scheduledTask instanceof ScheduledTask) {
                scheduleTask((ScheduledTask) scheduledTask);
            } else if (scheduledTask instanceof FixedDateTask) {
                scheduleTask((FixedDateTask) scheduledTask);
            }
        }
    }

    public void updateTask(TimedTask scheduledTask) {
        removeTask(scheduledTask.getId());
        addTask(scheduledTask);
    }

    private void scheduleTask(ScheduledTask scheduledTask) {
        scheduledFutureMap.put(scheduledTask.getId(), scheduledTaskRegistrar.getScheduler()
                .schedule(() -> executeTask(scheduledTask), new CronTrigger(scheduledTask.getCronExpression())));
    }

    private void scheduleTask(FixedDateTask scheduledTask) {
        scheduledFutureMap.put(scheduledTask.getId(), scheduledTaskRegistrar.getScheduler()
                .schedule(() -> executeTask(scheduledTask), scheduledTask.getExecutionTime()));
    }

    private void executeTask(TimedTask scheduledTask) {
        applicationContext.getBean(scheduledTask.getJobDefinitionBean(), Job.class).perform(scheduledTask);
    }

    public void removeTask(Long id) {
        Optional<ScheduledFuture> scheduledFuture = Optional.ofNullable(scheduledFutureMap.get(id));
        scheduledFuture.ifPresent(scheduledFuture1 -> {
            scheduledFuture1.cancel(true);
            scheduledFutureMap.remove(id);
        });
    }
}
