package com.capstone.util;

import java.util.Properties;
import java.util.Random;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.MimeMessage;

public class Sender {

	public boolean sendEmail(String code, String email, String name) {

		Properties properties = new Properties();
		properties.put("mail.smtp.host", "smtp.gmail.com");
		properties.put("mail.smtp.port", "587");
		properties.put("mail.smtp.auth", "true");
		properties.put("mail.smtp.starttls.enable", "true");
		Session session = Session.getInstance(properties, null);

		try {
			MimeMessage msg = new MimeMessage(session);
			msg.addRecipients(Message.RecipientType.TO, email);
			msg.setSubject("Authentication code for Capstone App");
			msg.setText("Hello, " + name + "!\n Enter this code in the form to change your password: "
					+ code + " \n Greetings.");
			Transport.send(msg, "capstonexdevelopers@gmail.com", "capstone2021");
			return true;
		} catch (MessagingException mex) {
			System.out.println("send failed, exception: " + mex);
			return false;
		}

	}

}
