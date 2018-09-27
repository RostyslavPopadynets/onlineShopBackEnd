package rostyslav.popadynets.service;

import rostyslav.popadynets.domain.mail.Mail;

public interface MailService {
	
	void sendMessage(Mail mail);

}
