package isa.adventuretime.Controller;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

import javax.mail.internet.AddressException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import isa.adventuretime.DTO.UnauthenticatedUserDTO;
import isa.adventuretime.Entity.Administrator;
import isa.adventuretime.Entity.BoatOwner;
import isa.adventuretime.Entity.CottageOwner;
import isa.adventuretime.Entity.FishingInstructor;
import isa.adventuretime.Entity.HeadEntityEnum;
import isa.adventuretime.Entity.RegisteredUser;
import isa.adventuretime.Entity.RequestForAdmin;
import isa.adventuretime.Entity.UnregisteredUser;
import isa.adventuretime.Entity.User;
import isa.adventuretime.Service.AdministratorService;
import isa.adventuretime.Service.BoatOwnerService;
import isa.adventuretime.Service.CottageOwnerService;
import isa.adventuretime.Service.FishingInstructorService;
import isa.adventuretime.Service.MailService;
import isa.adventuretime.Service.RegisteredUserService;
import isa.adventuretime.Service.RequestForAdminService;

@RestController
@RequestMapping(path = "/api/user")
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

	@Autowired
	RequestForAdminService requestForAdminService;

	@PostMapping(value = ("/login"), consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<User> userLogin(RequestEntity<ArrayList<String>> credentials) throws Exception {
		String email = credentials.getBody().get(0);
		String password = credentials.getBody().get(1);
		// System.out.println(email + " " + password);
		User newUser = (User) registeredUserService.findByEmail(email);
		if (newUser != null) {
			if (newUser.getAuthenticated() == true) {
				if (password.equals(newUser.getPassword())) {
					newUser.setUserType("registeredUser");
					return new ResponseEntity<User>(newUser, HttpStatus.OK);
				} else {
					return new ResponseEntity<User>(HttpStatus.NOT_ACCEPTABLE);
				}
			} else {
				return new ResponseEntity<User>(HttpStatus.UNAUTHORIZED);
			}
		}
		newUser = (User) boatOwnerService.findByEmail(email);
		if (newUser != null) {
			if (newUser.getAuthenticated() == true) {
				if (password.equals(newUser.getPassword())) {
					newUser.setUserType("boatOwner");
					return new ResponseEntity<User>(newUser, HttpStatus.OK);
				} else {
					return new ResponseEntity<User>(HttpStatus.NOT_ACCEPTABLE);
				}
			} else {
				return new ResponseEntity<User>(HttpStatus.UNAUTHORIZED);
			}
		}
		newUser = (User) cottageOwnerService.findByEmail(email);
		if (newUser != null) {
			if (newUser.getAuthenticated() == true) {
				if (password.equals(newUser.getPassword())) {
					newUser.setUserType("cottageOwner");
					return new ResponseEntity<User>(newUser, HttpStatus.OK);
				} else {
					return new ResponseEntity<User>(HttpStatus.NOT_ACCEPTABLE);
				}
			} else {
				return new ResponseEntity<User>(HttpStatus.UNAUTHORIZED);
			}
		}
		newUser = (User) fishingInstructorService.findByEmail(email);
		if (newUser != null) {
			if (newUser.getAuthenticated() == true) {
				if (password.equals(newUser.getPassword())) {
					newUser.setUserType("fishingInstructor");
					return new ResponseEntity<User>(newUser, HttpStatus.OK);
				} else {
					return new ResponseEntity<User>(HttpStatus.NOT_ACCEPTABLE);
				}
			} else {
				return new ResponseEntity<User>(HttpStatus.UNAUTHORIZED);
			}
		}
		newUser = (User) administratorService.findByEmail(email);
		if (newUser != null) {
			if (newUser.getAuthenticated() == true) {
				if (password.equals(newUser.getPassword())) {
					newUser.setUserType("administrator");
					return new ResponseEntity<User>(newUser, HttpStatus.OK);
				} else {
					return new ResponseEntity<User>(HttpStatus.NOT_ACCEPTABLE);
				}
			} else {
				return new ResponseEntity<User>(HttpStatus.UNAUTHORIZED);
			}
		}
		return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
	}

	@PostMapping(value = ("/update"), consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<User> userUpdate(RequestEntity<User> user) throws Exception {

		switch (user.getBody().getUserType()) {
			case "registeredUser":
				RegisteredUser registeredUser = registeredUserService.findByEmail(user.getBody().getEmail());
				registeredUser.updateWithUser(user.getBody());
				registeredUserService.saveRegisteredUser(registeredUser);
				return new ResponseEntity<User>((User) registeredUser, HttpStatus.OK);
			case "boatOwner":
				BoatOwner boatOwner = boatOwnerService.findByEmail(user.getBody().getEmail());
				boatOwner.updateWithUser(user.getBody());
				boatOwnerService.saveBoatOwner(boatOwner);
				return new ResponseEntity<User>((User) boatOwner, HttpStatus.OK);
			case "cottageOwner":
				CottageOwner cottageOwner = cottageOwnerService.findByEmail(user.getBody().getEmail());
				cottageOwner.updateWithUser(user.getBody());
				cottageOwnerService.saveCottageOwner(cottageOwner);
				return new ResponseEntity<User>((User) cottageOwner, HttpStatus.OK);
			case "fishingInstructor":
				FishingInstructor fishingInstructor = fishingInstructorService.findByEmail(user.getBody().getEmail());
				fishingInstructor.updateWithUser(user.getBody());
				fishingInstructorService.saveFishingInstructor(fishingInstructor);
				return new ResponseEntity<User>((User) fishingInstructor, HttpStatus.OK);
			case "administrator":
				Administrator administrator = administratorService.findByEmail(user.getBody().getEmail());
				administrator.updateWithUser(user.getBody());
				administratorService.saveAdministrator(administrator);
				return new ResponseEntity<User>((User) administrator, HttpStatus.OK);
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
				case "registeredUser":
					RegisteredUser registeredUser = new RegisteredUser(request.getBody().getName(),
							request.getBody().getLastname(), request.getBody().getEmail(),
							request.getBody().getPassword(),
							request.getBody().getAddress(), request.getBody().getCity(), request.getBody().getCountry(),
							request.getBody().getTelephoneNumber());
					registeredUserService.register(registeredUser);
					break;
				case "boatOwner":
					BoatOwner boatOwner = new BoatOwner(request.getBody().getName(), request.getBody().getLastname(),
							request.getBody().getEmail(), request.getBody().getPassword(),
							request.getBody().getAddress(),
							request.getBody().getCity(), request.getBody().getCountry(),
							request.getBody().getTelephoneNumber());
					boatOwnerService.register(boatOwner);
					break;
				case "cottageOwner":
					CottageOwner cottageOwner = new CottageOwner(request.getBody().getName(),
							request.getBody().getLastname(), request.getBody().getEmail(),
							request.getBody().getPassword(),
							request.getBody().getAddress(), request.getBody().getCity(), request.getBody().getCountry(),
							request.getBody().getTelephoneNumber());
					cottageOwnerService.register(cottageOwner);
					break;
				case "fishingInstructor":
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

	@GetMapping(path = "/getUnauthenticated")
	public ResponseEntity<ArrayList<UnauthenticatedUserDTO>> getUnauthenticated() {
		ArrayList<UnauthenticatedUserDTO> unauthenticatedUsers = new ArrayList<>();
		ArrayList<RequestForAdmin> requestForAdmins = requestForAdminService.findAll();

		ArrayList<BoatOwner> boatOwners = boatOwnerService.findAllByAuthenticated(false);
		ArrayList<CottageOwner> cottageOwners = cottageOwnerService.findAllByAuthenticated(false);
		ArrayList<FishingInstructor> fishingInstructors = fishingInstructorService.findAllByAuthenticated(false);

		for (RequestForAdmin requestForAdmin : requestForAdmins)
			switch (requestForAdmin.getForType()) {
				case BOAT_OWNER:
					for (BoatOwner boatOwner : boatOwners) {
						if (requestForAdmin.getRequesterId() == boatOwner.getId()
								&& requestForAdmin.getForType().equals(HeadEntityEnum.BOAT_OWNER)) {
							unauthenticatedUsers.add(
									new UnauthenticatedUserDTO(boatOwner, requestForAdmin, HeadEntityEnum.BOAT_OWNER));
							boatOwners.remove(boatOwner);
						}
						break;
					}
					break;
				case FISHING_INSTRUCTOR:
					for (FishingInstructor fishingInstructor : fishingInstructors) {
						if (requestForAdmin.getRequesterId() == fishingInstructor.getId()
								&& requestForAdmin.getForType().equals(HeadEntityEnum.FISHING_INSTRUCTOR)) {
							unauthenticatedUsers.add(new UnauthenticatedUserDTO(fishingInstructor, requestForAdmin,
									HeadEntityEnum.FISHING_INSTRUCTOR));
							fishingInstructors.remove(fishingInstructor);
						}
						break;
					}
					break;
				case COTTAGE_OWNER:
					for (CottageOwner cottageOwner : cottageOwners) {
						if (requestForAdmin.getRequesterId() == cottageOwner.getId()
								&& requestForAdmin.getForType().equals(HeadEntityEnum.COTTAGE_OWNER)) {
							unauthenticatedUsers.add(new UnauthenticatedUserDTO(cottageOwner, requestForAdmin,
									HeadEntityEnum.COTTAGE_OWNER));
							cottageOwners.remove(cottageOwner);
						}
						break;
					}
					break;
				default:
					System.err.println("Samo vlasnici traze zahteve");
					break;
			}

		return new ResponseEntity<ArrayList<UnauthenticatedUserDTO>>(unauthenticatedUsers, HttpStatus.OK);

	}

}