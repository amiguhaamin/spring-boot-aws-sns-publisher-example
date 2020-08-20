package com.rga.springbootawssnsproducerexample.controller;

import com.amazonaws.services.sns.AmazonSNSAsync;
import com.google.gson.Gson;
import com.rga.springbootawssnsproducerexample.config.SNSConfig;
import com.rga.springbootawssnsproducerexample.model.SamsungPhone;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController
public class AmazonSNSController {

    @GetMapping("/greeting/{fname}")
    String testCCPA(@PathVariable(name = "fname") String name) {
        return "Hi " + name + ", Welcome to CCPA project!";
    }

    @Value("${app.sns.topic}")
    private String snsTopic;

    @Autowired
    private SNSConfig snsConfig;

    @Autowired
    private AmazonSNSAsync amazonSNSAsync;

    @PostMapping("/publish")
    public void publishSNSMessage() throws Exception {

        List<SamsungPhone> samsungPhones = new ArrayList<>();

        SamsungPhone galaxyNote10Plus = new SamsungPhone();
        galaxyNote10Plus.setName("Samsung Galaxy Note 10 Plus");;
        galaxyNote10Plus.setDescription("2019 flagship phone with a 6.8 inch Super AMOLED display, S Pen, and much more");
        galaxyNote10Plus.setTimestamp(System.currentTimeMillis());
        samsungPhones.add(galaxyNote10Plus);

        SamsungPhone galaxyNote10 = new SamsungPhone();
        galaxyNote10.setName("Samsung Galaxy Note 10");;
        galaxyNote10.setDescription("2019 flagship phone with a 6.5 inch Super AMOLED display, S Pen, and much more");
        galaxyNote10.setTimestamp(System.currentTimeMillis());
        samsungPhones.add(galaxyNote10);

        SamsungPhone galaxyS10Plus = new SamsungPhone();
        galaxyS10Plus.setName("Samsung Galaxy S 10 Plus");;
        galaxyS10Plus.setDescription("Early 2019 flagship phone with a 6.5 inch Super AMOLED display, " +
                "dual punch hole selfie cameras, and much more");
        galaxyS10Plus.setTimestamp(System.currentTimeMillis());
        samsungPhones.add(galaxyS10Plus);

        SamsungPhone galaxyS10 = new SamsungPhone();
        galaxyS10.setName("Samsung Galaxy S 10");;
        galaxyS10.setDescription("Early 2019 flagship phone with a 6.3 inch Super AMOLED display, " +
                "dual punch hole selfie cameras, and much more");
        galaxyS10.setTimestamp(System.currentTimeMillis());
        samsungPhones.add(galaxyS10);

        for (SamsungPhone samsungPhone : samsungPhones) {
            this.snsConfig.publishSNSMessage(amazonSNSAsync, new Gson().toJson(samsungPhone));
        }
    }

    /*@Autowired
    private final NotificationMessagingTemplate notificationMessagingTemplate;

    @PostMapping("/publishMap")
    public ResponseEntity<Void> publish(@RequestBody SNSPublisherDTO dto)
            throws JsonProcessingException {
//        log.info("Publishing message {} to topic: {}", dto, snsTopic);

        ObjectMapper mapper = new ObjectMapper();

        *//*
            Make sure that the JSON Object message will be converted to string so that the subscriber
            can deserialize the message properly into a Map<> object otherwise the subscriber handler
            will throw a "NULL POINTER EXCEPTION"...
         *//*
        notificationMessagingTemplate.sendNotification(snsTopic, dto.getMessage(), dto.getSubject());
                *//*.sendNotification(snsTopic, mapper.writeValueAsString(dto.getMessage()),
                        dto.getSubject());*//*

        return ResponseEntity.ok().build();
    }*/


}
