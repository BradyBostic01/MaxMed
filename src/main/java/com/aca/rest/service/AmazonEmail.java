package com.aca.rest.service;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.nio.ByteBuffer;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.simpleemail.AmazonSimpleEmailService;
import com.amazonaws.services.simpleemail.AmazonSimpleEmailServiceClientBuilder;
import com.amazonaws.services.simpleemail.model.RawMessage;
import com.amazonaws.services.simpleemail.model.SendRawEmailRequest;

public class AmazonEmail {
	private static  String AWS_ACCESS_KEY_ID = "AKIAJUQILXTAHVGUT36Q";
	private static  String AWS_SECRET_ACCESS_KEY = "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";
	private static final String MYEMAIL = "Brady Bostic <bradybostic@gmail.com>";
	
	
	private static String SUBJECT = "Resume from Brady Bostic";
	
	private static String ATTACHMENT = "C:\\projects\\MaxMed\\src\\main\\webapp\\files\\Brady_Bostic_Resume.pdf";
	
	private static String BODY_TEXT = "Hello!\r\n"
									+"Thank you for coming to see my project! "
									+"Below is an attachment of my resume. "
									+"I appreciate your enthusiasm and consideration!";
	
	private static String BODY_HTML = "<html>"
									+ "<head></head>"
									+ "<body>"
									+ "<h1>Hello!</h1>"
									+ "<p>Thank you for coming to see my project! "
									+ "Below is an attachment of my resume. "
									+ "I appreciate your enthusiam and consideration!"
									+ "</body>"
									+ "</html>";
	
	public String sendEmail(String recipient) throws MessagingException{
		
	Session session = Session.getDefaultInstance(new Properties());
	
	MimeMessage message = new MimeMessage(session);
	
	message.setSubject(SUBJECT, "UTF-8");
	message.setFrom(new InternetAddress(MYEMAIL));
	message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipient));
	
	MimeMultipart msg_body = new MimeMultipart("alternative");
	
	MimeBodyPart wrap = new MimeBodyPart();
	
	MimeBodyPart textPart = new MimeBodyPart();
	textPart.setContent(BODY_TEXT, "text/plain; charset=UTF-8");
	
	MimeBodyPart htmlPart = new MimeBodyPart();
	htmlPart.setContent(BODY_HTML, "text/html; charset=UTF-8");
	
	msg_body.addBodyPart(textPart);
	msg_body.addBodyPart(htmlPart);
	
	wrap.setContent(msg_body);
	
	MimeMultipart msg = new MimeMultipart("mixed");
	
	message.setContent(msg);
	
	msg.addBodyPart(wrap);
	
	MimeBodyPart att = new MimeBodyPart();
	DataSource fds = new FileDataSource(ATTACHMENT);
	att.setDataHandler(new DataHandler(fds));
	att.setFileName(fds.getName());
	
	msg.addBodyPart(att);
	
	try{
		System.out.println("Attempting to send email through SES...");
		
		AWSCredentials credentials = new BasicAWSCredentials(AWS_ACCESS_KEY_ID, AWS_SECRET_ACCESS_KEY);
		AWSCredentialsProvider provider = new AWSStaticCredentialsProvider(credentials);
				AmazonSimpleEmailService client = AmazonSimpleEmailServiceClientBuilder.standard().withRegion(Regions.US_EAST_1).withCredentials(provider).build();
		
		PrintStream out = System.out;
		message.writeTo(out);
		
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		message.writeTo(outputStream);
		RawMessage rawMessage = new RawMessage(ByteBuffer.wrap(outputStream.toByteArray()));
		
		SendRawEmailRequest rawEmailRequest = new SendRawEmailRequest(rawMessage);
		
		client.sendRawEmail(rawEmailRequest);
		System.out.println("Email sent to " + recipient);
		return recipient;
	}catch(Exception e){
		System.out.println("Email failed!");
		System.err.println("Error message: " + e.getMessage());
		e.printStackTrace();
	}
	return recipient;
	}
}
