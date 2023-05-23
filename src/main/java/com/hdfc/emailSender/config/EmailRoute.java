package com.hdfc.emailSender.config;

import org.apache.camel.CamelContext;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class EmailRoute extends RouteBuilder {
	
	 
    @Value("${spring.mail.host}")
    private String mailHost;
    
    @Value("${spring.mail.username}")
    private String userName;
    
    @Value("${spring.mail.password}")
    private String emailPassword;
	
	public EmailRoute() {
	}

	public EmailRoute(CamelContext context) {
		super(context);

	}

	
	@Override
    public void configure() throws Exception {
		
		String toconfig= "smtps://"+mailHost+"?"+"username"+"="+userName+"&"+"password"+"="+emailPassword;		
        from("direct:sendEmail")
                .to(toconfig);
    }

}
