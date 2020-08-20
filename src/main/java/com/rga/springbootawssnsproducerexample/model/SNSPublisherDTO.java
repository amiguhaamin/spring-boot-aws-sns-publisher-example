package com.rga.springbootawssnsproducerexample.model;

import lombok.Data;

import java.io.Serializable;

@Data
public class SNSPublisherDTO implements Serializable {

    private static final long serialVersionUID = 3530284196279993488L;

    private String subject;

    private String message;

//    private Map<String, Object> message;
}
