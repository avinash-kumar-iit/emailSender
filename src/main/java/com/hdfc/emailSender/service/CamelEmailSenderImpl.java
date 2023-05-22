package com.hdfc.emailSender.service;

import java.util.HashMap;
import java.util.Map;

import org.apache.camel.CamelContext;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.impl.DefaultCamelContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

//@Service
@Component
public class CamelEmailSenderImpl {
	

//    private final ProducerTemplate producerTemplate;
//    
//    @Autowired
//    public CamelEmailSenderImpl(ProducerTemplate producerTemplate) {
//        this.producerTemplate = producerTemplate;
//    }
//    
    public String sendEmail() {
//    	Map<String, Object> headers = new HashMap<String, Object>();
//    	headers.put("To", "avinash.kumar.cse14@itbhu.ac.in");
//    	headers.put("From", "avinashkr.cseiitbhu@gmail.com");
//    	headers.put("Subject", "Camel rocks");
//    	//headers.put("CamelFileName", "fileOne");
//    	//headers.put("org.apache.camel.test", "value");
//        //producerTemplate.sendBody("direct:sendEmail", "Email body");
//    	//producerTemplate.send
//    	String body = "Hello Nishant.\nYes it does.\n\nRegards Avinahs.";
//        producerTemplate.sendBodyAndHeaders("direct:sendEmail",body, headers);
//        return "Email sending triggered";
        
        @SuppressWarnings("resource")
		CamelContext context = new DefaultCamelContext();
        
        try {
			context.addRoutes(new RouteBuilder() {
				
				@Override
			    public void configure() throws Exception {
			        from("direct:sendEmail")
//                        .setHeader("subject", constant("Test Email"))
//                        .setHeader("to", constant("avinashkr.cseiitbhu@gmail.com"))
//                        .setHeader("from", constant("avinashkr.cseiitbhu@gmail.com"))
//                        .setBody(constant("This is a test email"))
			                .to("smtps://smtp.gmail.com?username=avinashkr.cseiitbhu@gmail.com&password=ywavmpachqdgbufq");
			    }
				
			});
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        context.start();
        
        ProducerTemplate template = context.createProducerTemplate();
        
		Map<String,Object> headers = new HashMap<String,Object>();
		headers.put("To", "Guest Camel <avinash.kumar.cse14@itbhu.ac.in> ; avinashkr.cseiitbhu@gmail.com ; avinashkr.cseiitbhu@gmail.com ");
		headers.put("From", "User One <avinashkr.cseiitbhu@gmail.com>");
		headers.put("Subject", "Its therel");
		
		template.sendBodyAndHeaders("direct:sendEmail", "Test mail to many user of Apache", headers);

		// stop the CamelContext
		
		context.stop();
		
		return "sent message....";
        
    }

	
}
