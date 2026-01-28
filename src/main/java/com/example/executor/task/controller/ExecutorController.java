package com.example.executor.task.controller;

import com.example.executor.job.manager.JobManager;
import com.example.executor.repository.JobRepositoryManager;
import com.example.executor.repository.model.Job;
import com.example.executor.repository.model.Status;
import com.example.executor.task.controller.messages.ResponseMessage;
import com.example.executor.task.controller.messages.ResponseMessageAllJobs;
import com.example.executor.task.controller.messages.ResponseMessageJob;
import com.example.executor.task.controller.model.Task;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController(value = "/executor")
@AllArgsConstructor
public class ExecutorController {

    private JobRepositoryManager jobRepositoryManager;
    private JobManager jobManager;

    @PostMapping(value = "/execute")
    public ResponseEntity<ResponseMessage> doJob(@RequestBody Task task) {
        var job = transformTaskintoJob(task);
        var idOfJob = jobRepositoryManager.startJob(job);
        jobManager.doJob(job);
        return new ResponseEntity<>(new ResponseMessage("Job started.", String.valueOf(idOfJob)), HttpStatus.ACCEPTED);
    }

    @GetMapping(value = "/job/{id}")
    public ResponseEntity<ResponseMessageJob> getJobById(@PathVariable Long id) {
        Optional<Job> job = jobRepositoryManager.getJob(id);
        var resultJob = job.orElse(null);
        return new ResponseEntity<>(new ResponseMessageJob("Job status.", resultJob), HttpStatus.OK);
    }

    @GetMapping(value = "/jobs")
    public ResponseEntity<ResponseMessageAllJobs> getAllJobs() {
        var allJobs = jobRepositoryManager.getAllJobs();
        return new ResponseEntity<>(new ResponseMessageAllJobs("All jobs.", allJobs), HttpStatus.OK);
    }

    private Job transformTaskintoJob(Task task) {
        return Job.builder()
                .task(task)
                .status(Status.PENDING)
                .build();
    }
}
