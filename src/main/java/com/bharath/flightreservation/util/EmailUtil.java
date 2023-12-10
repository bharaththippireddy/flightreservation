package com.bharath.flightreservation.util;

import java.io.File;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

@Component
public class EmailUtil {
	@Value("${com.bharath.flightreservation.itinerary.email.body}")
	private String EMAIL_BODY = "Please find your Itinerary attached.";

	@Value("${com.bharath.flightreservation.itinerary.email.subject}")
	private String EMAIL_SUBJECT = "Itinerary for your Flight";

	private static final Logger LOGGER = LoggerFactory.getLogger(EmailUtil.class);

	@Autowired
	private JavaMailSender sender;

	public void sendItinerary(String toAddress, String filePath) {
		LOGGER.info("Inside sendItinerary()");

		MimeMessage message = sender.createMimeMessage();

		try {
			MimeMessageHelper messageHelper = new MimeMessageHelper(message, true);
			messageHelper.setTo(toAddress);
			messageHelper.setSubject(EMAIL_SUBJECT);
			messageHelper.setText(EMAIL_BODY);
			messageHelper.addAttachment("Itinearary", new File(filePath));
			sender.send(message);
		} catch (MessagingException e) {
			LOGGER.error("Exception inside sendItinerary" + e);
		}
	}

}
