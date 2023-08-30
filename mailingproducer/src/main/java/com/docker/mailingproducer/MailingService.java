package com.docker.mailingproducer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class MailingService {

    @Autowired
    private KafkaTemplate<String, Mail> kafkaProducer;

    @Value("${kafka.topic.mailing_topic}")
    private String mailingTopic;

    public void sendMail(Mail mail) {
        var future = kafkaProducer.send(mailingTopic, mail);
        future.whenComplete((sendResult, exception) -> {
            if (exception != null) {
                future.completeExceptionally(exception);
            } else {
                future.complete(sendResult);
            }
            System.out.println(sendResult);
        });
    }
}