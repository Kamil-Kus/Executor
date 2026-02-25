package com.example.executor.task.controller.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Device {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private TypeOfDevice typeOfDevice;
}
