package com.reviews.service;

import javax.mail.Message;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;

@Service
public class MailService {

	private JavaMailSender jms;

	@Autowired
	public MailService(JavaMailSender jms) {
		this.jms = jms;
	}

	public void sendEmail(final String senderEmailId, final String receiverEmailId, final String subject,
			final String message) {

		MimeMessagePreparator preparator = new MimeMessagePreparator() {

			public void prepare(MimeMessage mimeMessage) throws Exception {
				mimeMessage.setFrom(senderEmailId);
				mimeMessage.setRecipient(Message.RecipientType.TO, new InternetAddress(receiverEmailId));
				mimeMessage.setSubject(subject);
				mimeMessage.setText(message);
			}
		};

		try {
			jms.send(preparator);
			System.out.println("Message Sent");
		} catch (Exception exe) {
			exe.printStackTrace();
		}
	}

}
