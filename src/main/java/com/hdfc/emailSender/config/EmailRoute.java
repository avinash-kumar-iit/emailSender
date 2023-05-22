package com.hdfc.emailSender.config;

import org.apache.camel.CamelContext;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class EmailRoute extends RouteBuilder {
	
	public EmailRoute() {
	}

	public EmailRoute(CamelContext context) {
		super(context);

	}

	
	@Override
    public void configure() throws Exception {
        from("direct:sendEmail")
//                .setHeader("subject", constant("Test Email"))
//                .setHeader("to", constant("*******@gmail.com"))
//                .setHeader("from", constant("*******@gmail.com"))
//                .setBody(constant("This is a test email"))
                .to("smtps://smtp.gmail.com?username=*******@gmail.com&password=*******");
    }

}
