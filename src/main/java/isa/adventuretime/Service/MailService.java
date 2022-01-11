package isa.adventuretime.Service;

import java.io.UnsupportedEncodingException;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;

import com.google.common.collect.Lists;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.ozimov.springboot.mail.configuration.EnableEmailTools;
import it.ozimov.springboot.mail.model.Email;
import it.ozimov.springboot.mail.model.defaultimpl.DefaultEmail;
import it.ozimov.springboot.mail.service.EmailService;

@Service
@EnableEmailTools
public class MailService {

	@Autowired
	EmailService emailService;

	public void SendMail(String toEmail, String forWho, String body)
			throws AddressException, UnsupportedEncodingException {
		Email email = DefaultEmail.builder()
				.from(new InternetAddress("adventuretimesupport@protonmail.com", "Adventure Time Support 🧭"))
				.to(Lists.newArrayList(new InternetAddress(toEmail, forWho)))
				// TODO: change this to a real subject
				.subject("Deletion mail")
				.body(body)
				.encoding("UTF-8").build();
		emailService.send(email);
	}

	public void SendAuthenticationMail(String toEmail, String forWho)
			throws AddressException, UnsupportedEncodingException {
		Email email = DefaultEmail.builder()
				.from(new InternetAddress("adventuretimesupport@protonmail.com", "Adventure Time Support 🧭"))
				.to(Lists.newArrayList(new InternetAddress(toEmail, forWho)))
				.subject("Authentication mail")
				.body("http://localhost:3000/api/conf/" + toEmail.hashCode())
				.encoding("UTF-8").build();
		emailService.send(email);
	}
}
