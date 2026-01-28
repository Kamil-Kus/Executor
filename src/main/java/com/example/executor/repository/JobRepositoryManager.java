package com.example.executor.repository;

import com.example.executor.repository.model.Job;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class JobRepositoryManager {
    private JobDB jobDB;

    public Long startJob(Job job) {
        var save = jobDB.save(job);

        return save.getId();
    }

    public Optional<Job> getJob(Long id) {
        return jobDB.findById(id);
    }

    public List<Job> getAllJobs() {
        return jobDB.findAll();
    }
}
