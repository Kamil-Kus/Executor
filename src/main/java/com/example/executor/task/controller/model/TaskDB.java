package com.example.executor.task.controller.model;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskDB  extends JpaRepository<Task, Long> {
}
