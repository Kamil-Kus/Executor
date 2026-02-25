package com.example.executor.task.controller;

import com.example.executor.job.manager.JobManager;
import com.example.executor.repository.JobRepositoryManager;
import com.example.executor.repository.model.Job;
import com.example.executor.repository.model.Status;
import com.example.executor.task.controller.messages.ResponseMessage;
import com.example.executor.task.controller.messages.ResponseMessageAllJobs;
import com.example.executor.task.controller.messages.ResponseMessageJob;
import com.example.executor.task.controller.model.Device;
import com.example.executor.task.controller.model.Task;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@RestController(value = "/executor")
@AllArgsConstructor
public class ExecutorController {

    private JobRepositoryManager jobRepositoryManager;
    private JobManager jobManager;

    @PostMapping(value = "/execute", consumes = {"application/json"})
    public ResponseEntity<ResponseMessage> doJob(@RequestBody TaskDTO task) {
        var job = transformTaskintoJob(task);
        var idOfJob = jobRepositoryManager.startJob(job);
        jobManager.doJob(job);
        log.info("Job stared {}", job);
        log.info("Job {}: ", idOfJob);
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

    private Job transformTaskintoJob(TaskDTO task) {
        return Job.builder()
                .task(Task.builder()
                        .typeOfTask(task.getTypeOfTask())
                        .deviceList(transformIntoDeviceList(task.getDeviceList()))
                        .build())
                .status(Status.PENDING)
                .build();
    }

    private List<Device> transformIntoDeviceList(List<DeviceDTO> deviceList) {
        ArrayList<Device> devices = new ArrayList<>();
        deviceList.forEach(deviceDTO -> {
            devices.add(Device.builder()
                            .name(deviceDTO.getName())
                            .typeOfDevice(deviceDTO.getTypeOfDevice())
                            .build());
        });
        return devices;
    }
}
