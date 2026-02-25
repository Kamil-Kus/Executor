package com.example.executor.task.controller.messages;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class ResponseMessage {
    private String message;
    private String jobId;
}
