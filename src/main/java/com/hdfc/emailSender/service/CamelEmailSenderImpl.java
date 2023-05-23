package com.hdfc.emailSender.service;

import java.util.HashMap;
import java.util.Map;

import org.apache.camel.CamelContext;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.properties.PropertiesComponent;
import org.apache.camel.impl.DefaultCamelContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
public class CamelEmailSenderImpl {

	@Value("${success.email.body}")
	private String successMailBody;

	@Value("${failure.email.body}")
	private String failureMailBody;

	@Value("${spring.mail.host}")
	private String mailHost;

	@Value("${spring.mail.username}")
	private String userName;

	@Value("${spring.mail.password}")
	private String emailPassword;
	
	@Value("${success.email.subject}")
	private String successSub;
	
	@Value("${failure.email.subject}")
	private String failureSub;

	public String sendEmail(Map<String, Object> headers) {

		@SuppressWarnings("resource")
		CamelContext context = new DefaultCamelContext();

		try {
			String toconfig = "smtps://" + mailHost + "?" + "username" + "=" + userName + "&" + "password" + "="
					+ emailPassword;

			context.addRoutes(new RouteBuilder() {

				@Override
				public void configure() throws Exception {

//					PropertiesComponent pc = (PropertiesComponent) getContext().getComponent("properties");
//					pc.setLocation("classpath:application.properties");
					from("direct:sendEmail")
//                        .setHeader("subject", constant("Test Email"))
//                        .setHeader("to", constant("avinashkr.cseiitbhu@gmail.com"))
//                        .setHeader("from", constant("avinashkr.cseiitbhu@gmail.com"))
//                        .setBody(constant("This is a test email"))
							// .to("smtps://smtp.gmail.com?username=avinashkr.cseiitbhu@gmail.com&password=ywavmpachqdgbufq");
							.to(toconfig);

				}

			});
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		context.start();

//        PropertiesComponent pc = new PropertiesComponent();
//        pc.setLocation("classpath:com/mycompany/myprop.properties");
//        context.addComponent("properties", pc);.getComponent("properties");
		String mailBody = "";
		boolean check = true;

		ProducerTemplate template = context.createProducerTemplate();

		//Map<String, Object> headers = new HashMap<String, Object>();
		//headers.put("To", "Guest Camel <avinash.kumar.cse14@itbhu.ac.in> ; avinashkr.cseiitbhu@gmail.com; avinashkr.cseiitbhu@gmail.com");
		//headers.put("From", "User One <avinashkr.cseiitbhu@gmail.com>");
		// headers.put("Subject", "Apache Camel Mail sender Workong.............");

		// mailBody=(check)?successMailBody:failureMailBody;
		if (check) {
			headers.put("Subject", successSub);
			mailBody = successMailBody;
		} else {
			headers.put("Subject", failureSub);
			mailBody = failureMailBody;
		}
		template.sendBodyAndHeaders("direct:sendEmail", mailBody, headers);

		// stop the CamelContext

		context.stop();

		return "sent message....";

	}

}
