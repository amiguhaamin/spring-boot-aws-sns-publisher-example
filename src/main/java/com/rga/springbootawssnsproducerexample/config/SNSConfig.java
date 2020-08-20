package com.rga.springbootawssnsproducerexample.config;

import com.amazonaws.auth.DefaultAWSCredentialsProviderChain;
import com.amazonaws.services.sns.AmazonSNSAsync;
import com.amazonaws.services.sns.AmazonSNSAsyncClientBuilder;
import com.amazonaws.services.sns.model.PublishResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.aws.messaging.core.NotificationMessagingTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
public class SNSConfig {

    @Value("${cloud.aws.region.static}")
    private String awsRegion;

    @Value("${sns.topic.arn}")
    private String snsTopicARN;

    // Create an instance of Amazon SNS w/ Async functionality
    @Bean//(name = "amazonSNS", destroyMethod = "shutdown")
    public AmazonSNSAsync amazonSNSAsync() {
        return AmazonSNSAsyncClientBuilder
                .standard()
                .withCredentials(new DefaultAWSCredentialsProviderChain())
                .withRegion(awsRegion)
                .build();
    }

    public void publishSNSMessage(AmazonSNSAsync amazonSNSAsync, String message) {
        log.info("Publishing SNS message: " + message);
        PublishResult result = amazonSNSAsync.publish(this.snsTopicARN, message);
        log.info("SNS Message ID: " + result.getMessageId());
    }

    // Create a bean for Notif Messaging Template, this would require the "AmazonSNS" bean
    @Bean
    public NotificationMessagingTemplate notificationMessagingTemplate(
            AmazonSNSAsync amazonSNSAsync) {
        return new NotificationMessagingTemplate(amazonSNSAsync);
    }

}
