package com.example.redis.entity;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;

@Data
public class Student implements Serializable {
    private Integer id;
    private String name;
    private Double score;
    private LocalDate birthday;
}
