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

import ch.qos.logback.core.net.SyslogOutputStream;
import isa.adventuretime.Entity.Administrator;
import isa.adventuretime.Entity.BoatOwner;
import isa.adventuretime.Entity.CottageOwner;
import isa.adventuretime.Entity.FishingInstructor;
import isa.adventuretime.Entity.RegisteredUser;
import isa.adventuretime.Service.AdministratorService;
import isa.adventuretime.Service.BoatOwnerService;
import isa.adventuretime.Service.CottageOwnerService;
import isa.adventuretime.Service.FishingInstructorService;
import isa.adventuretime.Service.MailService;
import isa.adventuretime.Service.RegisteredUserService;

@RestController // Ili @Controller sa @ResponseBody, ne sme samo @Controller
@RequestMapping(path = "/api")
public class MainController {

	@Autowired
	private AdministratorService as;
	@Autowired
	private MailService mailService;

	@Autowired
	private RegisteredUserService registeredUserService;
	@Autowired
	private CottageOwnerService cottageOwnerService;
	@Autowired
	private BoatOwnerService boatOwnerService;
	@Autowired
	private FishingInstructorService fishingInstructorService;


	public MainController() {
	}
	//jakovljevic1999@gmail.com
	@GetMapping(path = "/mail")
	public void frontendSendMailTest() throws AddressException, UnsupportedEncodingException{
		String s = "stankovictab@gmail.com";
		mailService.SendMail("NMatijevic123@gmail.com", "Nikola");
	}

	@GetMapping(path = "/conf/{code}")
	public void confirmationMail(@PathVariable int code){
		RegisteredUser registeredUser = registeredUserService.findByEmailHash(code);
		if(registeredUser != null){
			registeredUser.setAuthenticated(true);
			registeredUserService.register(registeredUser);
			return;
		}
		CottageOwner cottageOwner = cottageOwnerService.findByEmailHash(code);
		if(cottageOwner !=null){
			cottageOwner.setAuthenticated(true);
			cottageOwnerService.register(cottageOwner);
			return;
		}
		BoatOwner boatOwner = boatOwnerService.findByEmailHash(code);
		if(boatOwner != null){
			boatOwner.setAuthenticated(true);
			boatOwnerService.register(boatOwner);
			return;
		}
		FishingInstructor fishingInstructor = fishingInstructorService.findByEmailHash(code);
		if(fishingInstructor != null){
			fishingInstructor.setAuthenticated(true);
			fishingInstructorService.register(fishingInstructor);
			return;
		}
		System.out.println("Nije nadjen sa tim codom "+code);
	}

	@GetMapping(path = "/get")
	public String frontendGETTest() {
		System.out.println(as.getById(1L).getEmail()); // 1L je long
		return as.getById(1L).getEmail();
	}

	@PostMapping(path = "/post", consumes = MediaType.ALL_VALUE)
	public String frontendPOSTTest(Integer num) {
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