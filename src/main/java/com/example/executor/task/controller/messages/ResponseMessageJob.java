package com.example.executor.task.controller.messages;

import com.example.executor.repository.model.Job;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class ResponseMessageJob {
    private String message;
    private Job job;
}
