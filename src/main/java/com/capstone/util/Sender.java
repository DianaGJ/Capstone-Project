package com.capstone.util;

import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.MimeMessage;

public class Sender {

	public boolean sendEmail(String code, String email, String name) {
		String password= "capstone2021";
		Properties properties = new Properties();
		properties.put("mail.smtp.host", "smtp.gmail.com");
		properties.put("mail.smtp.port", "587");
		properties.put("mail.smtp.auth", "true");
		properties.put("mail.smtp.starttls.enable", "true");
		Session session = Session.getInstance(properties, new Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(email, password );
			}
		});

		try {
			MimeMessage msg = new MimeMessage(session);
			msg.addRecipients(Message.RecipientType.TO, email);
			msg.setSubject("Authentication code for Capstone App");
			msg.setText("Hello, " + name + "!\n\nEnter this code in the form to change your password:\n"
					+ code + "\n\nGreetings,\n\n\nEasyPass Team");
			Transport.send(msg, "capstonexdevelopers@gmail.com", password);
			return true;
		} catch (MessagingException mex) {
			System.out.println("send failed, exception: " + mex);
			return false;
		}

	}

}
