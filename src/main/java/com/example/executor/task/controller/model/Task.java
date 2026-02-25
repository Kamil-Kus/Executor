package com.example.executor.task.controller.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private TypeOfTask typeOfTask;
    @OneToMany(cascade=CascadeType.ALL)
    private List<Device> deviceList;
}
