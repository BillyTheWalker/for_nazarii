package com.example.demo.taskengine.repository;

import com.example.demo.taskengine.models.base.Context;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContextRepository extends JpaRepository<Context, Long> {

}
