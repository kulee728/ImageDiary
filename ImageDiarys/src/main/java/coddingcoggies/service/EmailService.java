package coddingcoggies.service;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EmailService {

	private final JavaMailSender javaMailSender;
	
	private static final String senderEmail = "geonhwiahn@gmail.com"; 

	private static int number; 

	public static void createNum() {
		number = (int)(Math.random() * (90000)) + 100000;
	}
	
	public MimeMessage createMail(String mail) {
		createNum();
		MimeMessage message = javaMailSender.createMimeMessage();
		
		
		try {
			message.setFrom(senderEmail);
			message.setRecipients(MimeMessage.RecipientType.TO, mail);
			message.setSubject("이메일 인증");
			String emailContent ="";
			emailContent += "<h3>" + "요청하신 인증번호입니다." + "</h3>";
			emailContent += "<h1>" + number + "</h1>";
			message.setText(emailContent, "utf-8", "html");
			
		} catch (MessagingException e) {
			e.printStackTrace();
		}
			return message;
	}
	
	public int sendMail(String mail) {
		MimeMessage message = createMail(mail);
		javaMailSender.send(message);
		return number;
	}
}
