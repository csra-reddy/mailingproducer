package com.docker.mailingproducer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("api/v1")
@Slf4j
public class MailingController {

    private static final Logger logger = LoggerFactory.getLogger(MailingController.class);
    @Autowired
    private MailingService mailingService;

    @PostMapping("/mailing")
    public ResponseEntity<String> doMailing(@RequestBody Mail mail) {
        mailingService.sendMail(mail);
        logger.info("Received a request to send mailing: {}", mail);
        return new ResponseEntity<String>(HttpStatus.OK);
    }
}