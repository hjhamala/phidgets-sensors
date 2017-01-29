package fi.hjhamala.component;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;

@Service("sensorsEmail")
public class EmailAPI {
	@Autowired
	private MailSender sensorsEmail;
	
	public void readyToSendEmail(String toAddress, String fromAddress, String subject, String msgBody) {
		SimpleMailMessage message = new SimpleMailMessage();
		message.setFrom(fromAddress);
		message.setTo(toAddress);
		message.setSubject(subject);
		message.setText(msgBody);
		sensorsEmail.send(message);
	}
}
