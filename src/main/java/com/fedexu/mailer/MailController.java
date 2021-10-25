package com.fedexu.mailer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(value="/")
public class MailController {

    Logger logger = LoggerFactory.getLogger(MailController.class);

    @Autowired
    SendGridHelper sendGridHelper;

    private final String OK = "OK";
    private final String KO = "KO";

    @PostMapping("/send")
    private ResponseEntity<EmailData> sendEmail(@RequestBody EmailData emailData){
        try{
            emailData.setResult(sendGridHelper.sendMail(emailData.getEMail(), emailData.getName(), emailData.getBody()));
        }catch (Exception e){
            logger.error(e.getMessage(), e);
            return new ResponseEntity<>(emailData, HttpStatus.TOO_EARLY);
        }
        return new ResponseEntity<>(emailData, HttpStatus.OK);
    }


}
