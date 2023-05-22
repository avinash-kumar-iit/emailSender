package com.hdfc.emailSender;

import org.apache.camel.CamelContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import com.hdfc.emailSender.service.CamelEmailSenderImpl;

@SpringBootApplication
public class EmailSenderApplication {

	public static void main(String[] args) {
		SpringApplication.run(EmailSenderApplication.class, args);
//		ConfigurableApplicationContext context = SpringApplication.run(EmailSenderApplication.class, args);
//        CamelContext camelContext = context.getBean(CamelContext.class);
//
//        // Send email
//        camelContext.createProducerTemplate().sendBody("direct:sendEmail", "Email content");
//
//        // Shut down the application
//        SpringApplication.exit(context);
	}

}
