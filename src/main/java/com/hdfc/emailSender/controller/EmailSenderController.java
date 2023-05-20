package com.hdfc.emailSender.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.hdfc.emailSender.model.EmailRequest;
import com.hdfc.emailSender.service.EmailSenderImpl;

@RestController
public class EmailSenderController {
	
	@Autowired
	private  EmailSenderImpl emailService;

//    @Autowired
//    public EmailSenderController(EmailSenderImpl emailService) {
//        this.emailService = emailService;
//    }

//    @PostMapping(value="/send-email", consumes = { MediaType.APPLICATION_JSON_VALUE })
//    public void sendEmail(@RequestBody EmailRequest emailRequest) {
//        emailService.sendEmail(emailRequest.getTo(), emailRequest.getSubject(), emailRequest.getBody());
//    }
    @PostMapping("/send-email")
    public String  sendEmail() {
    	String s="******@gmail.com";
        emailService.sendEmail(s, "creating email by java code", "Hi Nirakar Sir"
        		+ " I am able to sent email by smtp server by using java sppring boot code "
        		+ "Thanks Avinash Kumar");
        return "Mail Sent Success!";
    }

}
