package rostyslav.popadynets.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import rostyslav.popadynets.domain.mail.Mail;
import rostyslav.popadynets.service.MailService;

@Service
public class MailServiceImpl implements MailService {

	@Autowired
	private JavaMailSender javaMailSender;

	@Override
	public void sendMessage(Mail mail) {
		
		SimpleMailMessage message = new SimpleMailMessage();
	
		message.setFrom(mail.getFrom());
		message.setTo(mail.getTo());
		message.setSubject(mail.getSubject());
		message.setText(mail.getContent());
	
		javaMailSender.send(message);
	}
	
	
}
