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

    public void SendMail(String toEmail, String forWho, String confLink) throws AddressException, UnsupportedEncodingException{
        Email email = DefaultEmail.builder()
        .from(new InternetAddress("adventuretimesupport@protonmail.com", "Adam Vuković"))
        .to(Lists.newArrayList(new InternetAddress(toEmail, forWho)))
        .subject("Eo ruke")
        .body(confLink)
        .encoding("UTF-8").build();

		emailService.send(email);
	
    }

}
