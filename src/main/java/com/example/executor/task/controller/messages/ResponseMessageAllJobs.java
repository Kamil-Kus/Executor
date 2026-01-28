package com.example.executor.task.controller.messages;

import com.example.executor.repository.model.Job;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@AllArgsConstructor
@Getter
public class ResponseMessageAllJobs {
    private String messsage;
    private List<Job> jobList;
}
