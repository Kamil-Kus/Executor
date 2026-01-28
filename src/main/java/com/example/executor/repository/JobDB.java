package com.example.executor.repository;

import com.example.executor.repository.model.Job;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JobDB extends JpaRepository<Job, Long> {
}
