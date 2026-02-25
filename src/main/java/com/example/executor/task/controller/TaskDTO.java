package com.example.executor.task.controller;

import com.example.executor.task.controller.model.TypeOfTask;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class TaskDTO {
    private TypeOfTask typeOfTask;
    private List<DeviceDTO> deviceList;
}
