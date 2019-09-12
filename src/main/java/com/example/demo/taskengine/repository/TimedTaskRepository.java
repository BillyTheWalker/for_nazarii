package com.example.demo.taskengine.repository;

import com.example.demo.taskengine.models.base.TimedTask;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TimedTaskRepository extends JpaRepository<TimedTask, Long> {
}
