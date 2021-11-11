package isa.adventuretime.Controller;

import java.io.UnsupportedEncodingException;

import javax.mail.internet.AddressException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import isa.adventuretime.Entity.Administrator;
import isa.adventuretime.Service.AdministratorService;
import isa.adventuretime.Service.MailService;

@RestController // Ili @Controller sa @ResponseBody, ne sme samo @Controller
@RequestMapping(path = "/api")
public class MainController {

	@Autowired
	private AdministratorService as;
	@Autowired
	private MailService mailService;

	public MainController() {
	}
	//jakovljevic1999@gmail.com
	@GetMapping(path = "/mail")
	public void frontendSendMailTest() throws AddressException, UnsupportedEncodingException{
		String s = "stankovictab@gmail.com";
		int hash = s.hashCode();
		mailService.SendMail("NMatijevic123@gmail.com", "Nikola", "http://localhost:8080/api/conf/"+hash);
	}

	@GetMapping(path = "/conf/{code}")
	public void confirmationMail(@PathVariable int code){
		System.out.println("AAAAAAAAAAAA "+code);
	}

	@GetMapping(path = "/get")
	public String frontendGETTest() {
		System.out.println(as.getById(1L).getEmail()); // 1L je long
		return as.getById(1L).getEmail();
	}

	@PostMapping(path = "/post", consumes = MediaType.ALL_VALUE)
	public String frontendPOSTTest(Integer num) {
		// TODO: Da li radi ovo num?
		System.out.println(as.getById(1L).getName() + " i dobijeno " + num + " sa fronta!");
		return as.getById(1L).getName();
	}

	// JSON Response na isti nacin kao sa Web-a, vanilla, bez koriscenja Jackson-a
	@GetMapping(path = "/get/json", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Administrator> frontendGETJSONTest() {
		Administrator test = as.getById(1L);
		System.out.println("JSON Sent Out!");
		return new ResponseEntity<>(test, HttpStatus.OK);
	}
}