package com.rga.springbootawssnsproducerexample.model;

import lombok.Data;

@Data
public class SchoolDetails {
    private String name;
    private String type;
    private String description;
    private long timestamp;
}
