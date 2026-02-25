package com.example.executor.task.controller;

import com.example.executor.task.controller.model.TypeOfDevice;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DeviceDTO {
    private String name;
    private TypeOfDevice typeOfDevice;
}
