package com.example.demo.taskengine.repository;

import com.example.demo.taskengine.models.base.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {

}
