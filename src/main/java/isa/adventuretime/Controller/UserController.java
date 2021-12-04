package isa.adventuretime.Controller;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

import javax.mail.internet.AddressException;
import javax.validation.constraints.Null;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import isa.adventuretime.Entity.BoatOwner;
import isa.adventuretime.Entity.CottageOwner;
import isa.adventuretime.Entity.FishingInstructor;
import isa.adventuretime.Entity.RegisteredUser;
import isa.adventuretime.Entity.UnregisteredUser;
import isa.adventuretime.Entity.User;
import isa.adventuretime.Service.AdministratorService;
import isa.adventuretime.Service.BoatOwnerService;
import isa.adventuretime.Service.CottageOwnerService;
import isa.adventuretime.Service.FishingInstructorService;
import isa.adventuretime.Service.MailService;
import isa.adventuretime.Service.RegisteredUserService;

@RestController
@RequestMapping(path = "/api/register")
public class UserController {

	@Autowired
	private RegisteredUserService registeredUserService;

	@Autowired
	private BoatOwnerService boatOwnerService;

	@Autowired
	private CottageOwnerService cottageOwnerService;

	@Autowired
	private FishingInstructorService fishingInstructorService;

	@Autowired
	private AdministratorService administratorService;

	@Autowired
	private MailService mailService;

	@PostMapping(
			value =("/login"),
		    consumes = MediaType.APPLICATION_JSON_VALUE,   
		    produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<User> UserLogin(@RequestBody ArrayList<String> credentials) throws Exception{
		
		String email = credentials.get(0);
		String password = credentials.get(1);

		System.out.println(email + " " + password);

		User newUser = (User)registeredUserService.findByEmail(email);
		if(newUser != null){
			newUser.setUserType("Registered User");
			return new ResponseEntity<User>(newUser, HttpStatus.OK);
		}
		newUser = (User)boatOwnerService.findByEmail(email);
		if(newUser != null){
			newUser.setUserType("Boat Owner");
			return new ResponseEntity<User>(newUser, HttpStatus.OK);
		}
		newUser = (User)cottageOwnerService.findByEmail(email);
		if(newUser != null){
			newUser.setUserType("Cottage Owner");
			return new ResponseEntity<User>(newUser, HttpStatus.OK);
		}
		newUser = (User)fishingInstructorService.findByEmail(email);
		if(newUser != null){
			newUser.setUserType("Fishing Instructor");
			return new ResponseEntity<User>(newUser, HttpStatus.OK);
		}
		newUser = (User)administratorService.findByEmail(email);
		if(newUser != null){
			newUser.setUserType("Admin");
			return new ResponseEntity<User>(newUser, HttpStatus.OK);
		}
		return null;
	}

	@PostMapping(path = "/create", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> registerUser(RequestEntity<UnregisteredUser> request)
			throws AddressException, UnsupportedEncodingException {

		String new_user_email = request.getBody().getEmail();
		String returnedString = "User has been successfully created!";
		System.out.println(request.getBody().getEmail());
		System.out.println(request.getBody().getAddress());
		System.out.println(request.getBody().getCity());
		System.out.println(request.getBody().getCountry());

		if (registeredUserService.findByEmail(new_user_email) == null
				&& boatOwnerService.findByEmail(new_user_email) == null
				&& cottageOwnerService.findByEmail(new_user_email) == null
				&& fishingInstructorService.findByEmail(new_user_email) == null) {

			switch (request.getBody().getType()) {
				case "Standard User":
					RegisteredUser registeredUser = new RegisteredUser(request.getBody().getName(),
							request.getBody().getLastname(), request.getBody().getEmail(),
							request.getBody().getPassword(),
							request.getBody().getAddress(), request.getBody().getCity(), request.getBody().getCountry(),
							request.getBody().getTelephoneNumber());
					registeredUserService.register(registeredUser);
					break;
				case "Boat Owner":
					BoatOwner boatOwner = new BoatOwner(request.getBody().getName(), request.getBody().getLastname(),
							request.getBody().getEmail(), request.getBody().getPassword(),
							request.getBody().getAddress(),
							request.getBody().getCity(), request.getBody().getCountry(),
							request.getBody().getTelephoneNumber());
					boatOwnerService.register(boatOwner);
					break;
				case "Cottage Owner":
					System.out.println("");
					CottageOwner cottageOwner = new CottageOwner(request.getBody().getName(),
							request.getBody().getLastname(), request.getBody().getEmail(),
							request.getBody().getPassword(),
							request.getBody().getAddress(), request.getBody().getCity(), request.getBody().getCountry(),
							request.getBody().getTelephoneNumber());
					cottageOwnerService.register(cottageOwner);
					break;
				case "Fishing Instructor":
					FishingInstructor fishingInstructor = new FishingInstructor(request.getBody().getName(),
							request.getBody().getLastname(), request.getBody().getEmail(),
							request.getBody().getPassword(),
							request.getBody().getAddress(), request.getBody().getCity(), request.getBody().getCountry(),
							request.getBody().getTelephoneNumber());
					fishingInstructorService.register(fishingInstructor);
					break;
				default:
					returnedString = "Error - Unrecognized type!";

			}
		} else {
			System.out.println("Error - User with that E-mail already exists.");
			returnedString = "Error - User with that E-mail already exists.";
		}
		mailService.SendMail(request.getBody().getEmail(), request.getBody().getName());
		return new ResponseEntity<String>(returnedString, HttpStatus.OK);

	}

}
