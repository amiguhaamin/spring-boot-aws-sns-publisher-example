package com.rga.springbootawssnsproducerexample.controller;

import com.amazonaws.services.sns.AmazonSNSAsync;
import com.google.gson.Gson;
import com.rga.springbootawssnsproducerexample.config.SNSConfig;
import com.rga.springbootawssnsproducerexample.model.SchoolDetails;
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

        List<SchoolDetails> schoolList = new ArrayList<>();

        SchoolDetails elementary = new SchoolDetails();
        elementary.setName("Aukamm Elementary School");
        elementary.setType("Elementary school");
        elementary.setDescription("This school is supported by the Kaiserslautern School District.");
        elementary.setTimestamp(System.currentTimeMillis());
        schoolList.add(elementary);

        SchoolDetails middleSchool = new SchoolDetails();
        middleSchool.setName("Community Day Charter School");
        middleSchool.setType("Middle school");
        middleSchool.setDescription("Community Day Charter was founded in 1995 and was one of the first charter schools in Massachusetts.");
        middleSchool.setTimestamp(System.currentTimeMillis());
        schoolList.add(middleSchool);

        SchoolDetails internationalSchool = new SchoolDetails();
        internationalSchool.setName("International School Moshi");
        internationalSchool.setType("International school");
        internationalSchool.setDescription("International School Moshi is a fully accredited international school " +
                "providing an education for children from both the local and expatriate communities from age 3 to age 19.");
        internationalSchool.setTimestamp(System.currentTimeMillis());
        schoolList.add(internationalSchool);

        SchoolDetails bSchool = new SchoolDetails();
        bSchool.setName("IIMA");
        bSchool.setType("Business school");
        bSchool.setDescription("The school has been accorded the status of an Institute " +
                "of National Importance by Ministry of Human Resources, Government of India in 2017.");
        bSchool.setTimestamp(System.currentTimeMillis());
        schoolList.add(galaxyS10);

        for (SchoolDetails school : schoolList) {
            this.snsConfig.publishSNSMessage(amazonSNSAsync, new Gson().toJson(school));
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
