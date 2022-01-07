package isa.adventuretime.Controller;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import javax.mail.internet.AddressException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import isa.adventuretime.DTO.UnauthenticatedUserDTO;
import isa.adventuretime.Entity.Administrator;
import isa.adventuretime.Entity.BoatOwner;
import isa.adventuretime.Entity.CottageOwner;
import isa.adventuretime.Entity.DeletionRequest;
import isa.adventuretime.Entity.FishingInstructor;
import isa.adventuretime.Entity.HeadEntityEnum;
import isa.adventuretime.Entity.RegisteredUser;
import isa.adventuretime.Entity.RequestForAdmin;
import isa.adventuretime.Entity.User;
import isa.adventuretime.Service.AdministratorService;
import isa.adventuretime.Service.BoatOwnerService;
import isa.adventuretime.Service.CottageOwnerService;
import isa.adventuretime.Service.DeletionRequestService;
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
	private RequestForAdminService requestForAdminService;

	@Autowired
	private DeletionRequestService deletionRequestService;

	// @Autowired
	// private SubscriptionService subscriptionService;

	@PostMapping(value = ("/login"), consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<User> userLogin(RequestEntity<ArrayList<String>> credentials) throws Exception {
		String email = credentials.getBody().get(0);
		String password = credentials.getBody().get(1);
		User newUser = (User) registeredUserService.findByEmail(email);
		if (newUser != null) {
			if (newUser.getDeleted()) {
				return new ResponseEntity<User>(HttpStatus.FORBIDDEN);
			}
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
			if (newUser.getDeleted()) {
				return new ResponseEntity<User>(HttpStatus.FORBIDDEN);
			}
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
			if (newUser.getDeleted()) {
				return new ResponseEntity<User>(HttpStatus.FORBIDDEN);
			}
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
			if (newUser.getDeleted()) {
				return new ResponseEntity<User>(HttpStatus.FORBIDDEN);
			}
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
			if (newUser.getDeleted()) {
				return new ResponseEntity<User>(HttpStatus.FORBIDDEN);
			}
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

	@PostMapping(value = ("/update"))
	public ResponseEntity<User> userUpdate(RequestEntity<String> user) throws Exception {

		String splitArray[] = user.getBody().split(",");
		String split2[] = new String[20];
		for (String s : splitArray) {
			if (s.contains("userType")) {
				split2 = s.split("\"");
				break;
			}
		}

		String userTypeString = split2[split2.length - 1];
		ObjectMapper mapper = new ObjectMapper();

		switch (userTypeString) {
			case "registeredUser":
				RegisteredUser newRegisteredUser = mapper.readValue(user.getBody(), RegisteredUser.class);
				RegisteredUser registeredUser = registeredUserService.findByEmail(newRegisteredUser.getEmail());
				registeredUser.updateUserWithUser(newRegisteredUser);
				registeredUserService.saveRegisteredUser(registeredUser);
				return new ResponseEntity<User>((User) registeredUser, HttpStatus.OK);
			case "boatOwner":
				BoatOwner newBoatOwner = mapper.readValue(user.getBody(), BoatOwner.class);
				BoatOwner boatOwner = boatOwnerService.findByEmail(newBoatOwner.getEmail());
				boatOwner.updateUserWithUser(newBoatOwner);
				boatOwnerService.saveBoatOwner(boatOwner);
				return new ResponseEntity<User>((User) boatOwner, HttpStatus.OK);
			case "cottageOwner":
				CottageOwner newCottageOwner = mapper.readValue(user.getBody(), CottageOwner.class);
				CottageOwner cottageOwner = cottageOwnerService.findByEmail(newCottageOwner.getEmail());
				cottageOwner.updateUserWithUser(newCottageOwner);
				cottageOwnerService.saveCottageOwner(cottageOwner);
				return new ResponseEntity<User>((User) cottageOwner, HttpStatus.OK);
			case "fishingInstructor":
				FishingInstructor newFishingInstructor = mapper.readValue(user.getBody(), FishingInstructor.class);
				FishingInstructor fishingInstructor = fishingInstructorService
						.findByEmail(newFishingInstructor.getEmail());
				fishingInstructor.updateFishingInstructor(newFishingInstructor,
						newFishingInstructor.getStartWorkPeriod(), newFishingInstructor.getEndWorkPeriod());
				fishingInstructor = fishingInstructorService.saveFishingInstructor(fishingInstructor);
				return new ResponseEntity<User>((User) fishingInstructor, HttpStatus.OK);
			case "administrator":
				Administrator newAdministrator = mapper.readValue(user.getBody(), Administrator.class);
				Administrator administrator = administratorService.findByEmail(newAdministrator.getEmail());
				administrator.updateUserWithUser(newAdministrator);
				administratorService.saveAdministrator(administrator);
				return new ResponseEntity<User>((User) administrator, HttpStatus.OK);
		}
		return null;
	}

	@PostMapping(path = "/create", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> registerUser(RequestEntity<UnauthenticatedUserDTO> request)
			throws AddressException, UnsupportedEncodingException {

		// TODO: Posto su dodati deleted i userType u User klasi,
		// sad moraju svi konstruktori da se menjaju

		String new_user_email = request.getBody().getEmail();
		String returnedString = "User has been successfully created!";

		RequestForAdmin requestForAdmin = new RequestForAdmin();

		if (administratorService.findByEmail(new_user_email) == null
				&& registeredUserService.findByEmail(new_user_email) == null
				&& boatOwnerService.findByEmail(new_user_email) == null
				&& cottageOwnerService.findByEmail(new_user_email) == null
				&& fishingInstructorService.findByEmail(new_user_email) == null) {
			switch (request.getBody().getUserType()) {
				case "administrator":
					Administrator administrator = new Administrator(request.getBody().getName(),
							request.getBody().getLastname(), request.getBody().getEmail(),
							request.getBody().getPassword(),
							request.getBody().getAddress(), request.getBody().getCity(), request.getBody().getCountry(),
							request.getBody().getTelephoneNumber());
					administratorService.register(administrator);
					break;
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
					boatOwner.setAuthenticated(false);
					BoatOwner newBoatOwner = boatOwnerService.register(boatOwner);

					requestForAdmin = new RequestForAdmin(
							newBoatOwner.getId(),
							HeadEntityEnum.BOAT_OWNER,
							request.getBody().getUserRegistrationReason());
					requestForAdminService.saveRequestForAdmin(requestForAdmin);

					break;
				case "cottageOwner":
					CottageOwner cottageOwner = new CottageOwner(request.getBody().getName(),
							request.getBody().getLastname(), request.getBody().getEmail(),
							request.getBody().getPassword(),
							request.getBody().getAddress(), request.getBody().getCity(), request.getBody().getCountry(),
							request.getBody().getTelephoneNumber());
					cottageOwner.setAuthenticated(false);
					CottageOwner newCottageOwner = cottageOwnerService.register(cottageOwner);

					requestForAdmin = new RequestForAdmin(
							newCottageOwner.getId(),
							HeadEntityEnum.COTTAGE_OWNER,
							request.getBody().getUserRegistrationReason());
					requestForAdminService.saveRequestForAdmin(requestForAdmin);

					break;
				case "fishingInstructor":
					FishingInstructor fishingInstructor = new FishingInstructor(request.getBody().getName(),
							request.getBody().getLastname(), request.getBody().getEmail(),
							request.getBody().getPassword(),
							request.getBody().getAddress(), request.getBody().getCity(), request.getBody().getCountry(),
							request.getBody().getTelephoneNumber());
					fishingInstructor.setAuthenticated(false);
					FishingInstructor newFishingInstructor = fishingInstructorService.register(fishingInstructor);

					requestForAdmin = new RequestForAdmin(
							newFishingInstructor.getId(),
							HeadEntityEnum.FISHING_INSTRUCTOR,
							request.getBody().getUserRegistrationReason());
					requestForAdminService.saveRequestForAdmin(requestForAdmin);

					break;
				default:
					returnedString = "Error - Unrecognized type!";

			}
		} else {
			returnedString = "Error - User with that E-mail already exists.";
			System.out.println(returnedString);
		}
		mailService.SendAuthenticationMail(request.getBody().getEmail(), request.getBody().getName());
		return new ResponseEntity<String>(returnedString, HttpStatus.OK);
	}

	@GetMapping(path = "/getUnauthenticated")
	public ResponseEntity<ArrayList<UnauthenticatedUserDTO>> getUnauthenticated() {
		ArrayList<UnauthenticatedUserDTO> unauthenticatedUsers = new ArrayList<>();
		ArrayList<RequestForAdmin> requestForAdmins = requestForAdminService.findAll();

		ArrayList<BoatOwner> boatOwners = boatOwnerService.findAllByAuthenticatedAndDenied(false, false);
		ArrayList<CottageOwner> cottageOwners = cottageOwnerService.findAllByAuthenticatedAndDenied(false, false);
		ArrayList<FishingInstructor> fishingInstructors = fishingInstructorService.findAllByAuthenticatedAndDenied(false, false);

		for (RequestForAdmin requestForAdmin : requestForAdmins)
			switch (requestForAdmin.getForType()) {
				case BOAT_OWNER:
					for (BoatOwner boatOwner : boatOwners) {
						if (requestForAdmin.getRequesterId() == boatOwner.getId()
								&& requestForAdmin.getForType().equals(HeadEntityEnum.BOAT_OWNER)) {
							unauthenticatedUsers.add(
									new UnauthenticatedUserDTO(boatOwner, requestForAdmin, HeadEntityEnum.BOAT_OWNER, boatOwner.getId()));
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
									HeadEntityEnum.FISHING_INSTRUCTOR, fishingInstructor.getId()));
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
									HeadEntityEnum.COTTAGE_OWNER, cottageOwner.getId()));
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

	@GetMapping(path = "/getDeletionRequests")
	public ResponseEntity<ArrayList<DeletionRequest>> getDeletionRequests() {
		ArrayList<DeletionRequest> deletionRequests = deletionRequestService.getAll();

		return new ResponseEntity<ArrayList<DeletionRequest>>(deletionRequests, HttpStatus.OK);
	}

	@PostMapping(path = "/deleteUser/{id}")
	public ResponseEntity<Boolean> deleteUser(@PathVariable("id") Long id, RequestEntity<String> userType)
			throws AddressException, UnsupportedEncodingException {		

		switch (userType.getBody()) {
			case "\"ADMINISTRATOR\"":
				Administrator administrator = administratorService.getById(id);
				mailService.SendMail(administrator.getEmail(), administrator.getName(),
						"Your account has been deleted. \nThank you for using our services! \n\nSincerely, Adventure Time.");
				return new ResponseEntity<Boolean>(administratorService.markDeleted(id), HttpStatus.OK);
			case "\"REGISTERED_USER\"":
				RegisteredUser registeredUser = registeredUserService.getById(id);
				mailService.SendMail(registeredUser.getEmail(), registeredUser.getName(),
						"Your account has been deleted. \nThank you for using our services! \n\nSincerely, Adventure Time.");
				return new ResponseEntity<Boolean>(registeredUserService.markDeleted(id), HttpStatus.OK);

			case "\"BOAT_OWNER\"":
				BoatOwner boatOwner = boatOwnerService.getById(id);
				mailService.SendMail(boatOwner.getEmail(), boatOwner.getName(),
						"Your account has been deleted. \nThank you for using our services! \n\nSincerely, Adventure Time.");
				return new ResponseEntity<Boolean>(boatOwnerService.markDeleted(id), HttpStatus.OK);

			case "\"COTTAGE_OWNER\"":
				CottageOwner cottageOwner = cottageOwnerService.getById(id);
				mailService.SendMail(cottageOwner.getEmail(), cottageOwner.getName(),
						"Your account has been deleted. \nThank you for using our services! \n\nSincerely, Adventure Time.");
				return new ResponseEntity<Boolean>(cottageOwnerService.markDeleted(id), HttpStatus.OK);

			case "\"FISHING_INSTRUCTOR\"":
				FishingInstructor fishingInstructor = fishingInstructorService.getById(id);
				mailService.SendMail(fishingInstructor.getEmail(), fishingInstructor.getName(),
						"Your account has been deleted. \nThank you for using our services! \n\nSincerely, Adventure Time.");
				return new ResponseEntity<Boolean>(fishingInstructorService.markDeleted(id), HttpStatus.OK);

			default:
				return new ResponseEntity<Boolean>(false, HttpStatus.OK);
		}
	}

	@PostMapping(path = "/denyUserDeletion/{id}")
	public ResponseEntity<Boolean> denyUserDeletion(@PathVariable("id") Long id, RequestEntity<String> request)
			throws AddressException, UnsupportedEncodingException {

		// {"type":"BOAT_OWNER","reason":"test"}

		String splitArray[] = request.getBody().split(",");
		String typeArray[] = splitArray[0].split(":");
		String reasonArray[] = splitArray[1].split(":");
		String type = typeArray[1].replaceAll("\"", "");
		String reason = reasonArray[1].replaceAll("\"", "").replaceAll("}", "");

		switch (type) {

			case "BOAT_OWNER":
				BoatOwner boatOwner = boatOwnerService.getById(id);
				deletionRequestService
						.delete(deletionRequestService.getByForTypeAndRequesterId(HeadEntityEnum.BOAT_OWNER, id));
				mailService.SendMail(boatOwner.getEmail(), boatOwner.getName(),
						"Your request for account deletion has been denied by an administrator.\nThe reason for the denial for the deletion of your account is :\n"
								+ reason);
				return new ResponseEntity<Boolean>(true, HttpStatus.OK);

			case "COTTAGE_OWNER":
				CottageOwner cottageOwner = cottageOwnerService.getById(id);
				deletionRequestService
						.delete(deletionRequestService.getByForTypeAndRequesterId(HeadEntityEnum.COTTAGE_OWNER, id));
				mailService.SendMail(cottageOwner.getEmail(), cottageOwner.getName(),
						"Your request for account deletion has been denied by an administrator.\nThe reason for the denial for the deletion of your account is :\n"
								+ reason);
				return new ResponseEntity<Boolean>(true, HttpStatus.OK);

			case "FISHING_INSTRUCTOR":
				FishingInstructor fishingInstructor = fishingInstructorService.getById(id);
				deletionRequestService
						.delete(deletionRequestService.getByForTypeAndRequesterId(HeadEntityEnum.FISHING_INSTRUCTOR,
								id));
				mailService.SendMail(fishingInstructor.getEmail(), fishingInstructor.getName(),
						"Your request for account deletion has been denied by an administrator.\nThe reason for the denial for the deletion of your account is :\n"
								+ reason);
				return new ResponseEntity<Boolean>(true, HttpStatus.OK);

			default:
				return new ResponseEntity<Boolean>(false, HttpStatus.OK);

		}
	}

	@PostMapping(path = "createDeletionRequest/{id}")
	public ResponseEntity<Boolean> createDeletionRequest(@PathVariable("id") Long id, RequestEntity<String> request)
			throws AddressException, UnsupportedEncodingException {

		// {"type":"BOAT_OWNER","reason":"test"}

		String splitArray[] = request.getBody().split(",");
		String typeArray[] = splitArray[0].split(":");
		String reasonArray[] = splitArray[1].split(":");
		String type = typeArray[1].replaceAll("\"", "");
		String reason = reasonArray[1].replaceAll("\"", "").replaceAll("}", "");

		switch (type) {
			case "REGISTERED_USER":
				RegisteredUser registeredUser = registeredUserService.getById(id);
				// registeredUserService.createDeletionRequest(id, reason);
				mailService.SendMail(registeredUser.getEmail(), registeredUser.getName(),
						"Your request for account deletion has been sent to an administrator.\nThe reason for the deletion of your account is :\n"
								+ reason);
				return new ResponseEntity<Boolean>(true, HttpStatus.OK);

			case "BOAT_OWNER":
				BoatOwner boatOwner = boatOwnerService.getById(id);
				if (deletionRequestService.getByForTypeAndRequesterId(HeadEntityEnum.BOAT_OWNER, id) != null) {
					return new ResponseEntity<Boolean>(false, HttpStatus.TOO_MANY_REQUESTS);
				}
				boatOwnerService.createDeletionRequest(id, reason);
				mailService.SendMail(boatOwner.getEmail(), boatOwner.getName(),
						"Your request for account deletion has been sent to an administrator.\nThe reason for the deletion of your account is :\n"
								+ reason);
				return new ResponseEntity<Boolean>(true, HttpStatus.OK);

			case "COTTAGE_OWNER":
				CottageOwner cottageOwner = cottageOwnerService.getById(id);
				if (deletionRequestService.getByForTypeAndRequesterId(HeadEntityEnum.COTTAGE_OWNER, id) != null) {
					return new ResponseEntity<Boolean>(false, HttpStatus.TOO_MANY_REQUESTS);
				}
				cottageOwnerService.createDeletionRequest(id, reason);
				mailService.SendMail(cottageOwner.getEmail(), cottageOwner.getName(),
						"Your request for account deletion has been sent to an administrator.\nThe reason for the deletion of your account is :\n"
								+ reason);
				return new ResponseEntity<Boolean>(true, HttpStatus.OK);

			case "FISHING_INSTRUCTOR":
				BoatOwner fishingInstructor = boatOwnerService.getById(id);
				if (deletionRequestService.getByForTypeAndRequesterId(HeadEntityEnum.FISHING_INSTRUCTOR, id) != null) {
					return new ResponseEntity<Boolean>(false, HttpStatus.TOO_MANY_REQUESTS);
				}
				fishingInstructorService.createDeletionRequest(id, reason);
				mailService.SendMail(fishingInstructor.getEmail(), fishingInstructor.getName(),
						"Your request for account deletion has been sent to an administrator.\nThe reason for the deletion of your account is :\n"
								+ reason);
				return new ResponseEntity<Boolean>(true, HttpStatus.OK);

			default:
				return new ResponseEntity<Boolean>(false, HttpStatus.OK);

		}
	}

	@PostMapping(path = "/approveRegistrationRequest/{id}")
	public ResponseEntity<Boolean> approveCreationRequest(@PathVariable("id") Long id, RequestEntity<String> userType)
	throws AddressException, UnsupportedEncodingException {	

		switch (userType.getBody()) {		
			case "\"BOAT_OWNER\"":
				BoatOwner boatOwner = boatOwnerService.getById(id);
				mailService.SendMail(boatOwner.getEmail(), boatOwner.getName(),
						"Your account has been created. \nThank you for using our services! \n\nSincerely, Adventure Time.");
				requestForAdminService.delete(requestForAdminService.findByForTypeAndRequesterId(HeadEntityEnum.BOAT_OWNER, id));
				return new ResponseEntity<Boolean>(boatOwnerService.markAuthenticated(id), HttpStatus.OK);

			case "\"COTTAGE_OWNER\"":
				CottageOwner cottageOwner = cottageOwnerService.getById(id);
				mailService.SendMail(cottageOwner.getEmail(), cottageOwner.getName(),
						"Your account has been created. \nThank you for using our services! \n\nSincerely, Adventure Time.");
				requestForAdminService.delete(requestForAdminService.findByForTypeAndRequesterId(HeadEntityEnum.COTTAGE_OWNER, id));
				return new ResponseEntity<Boolean>(cottageOwnerService.markAuthenticated(id), HttpStatus.OK);

			case "\"FISHING_INSTRUCTOR\"":
				FishingInstructor fishingInstructor = fishingInstructorService.getById(id);
				mailService.SendMail(fishingInstructor.getEmail(), fishingInstructor.getName(),
						"Your account has been created. \nThank you for using our services! \n\nSincerely, Adventure Time.");
				requestForAdminService.delete(requestForAdminService.findByForTypeAndRequesterId(HeadEntityEnum.FISHING_INSTRUCTOR, id));
				return new ResponseEntity<Boolean>(fishingInstructorService.markAuthenticated(id), HttpStatus.OK);

			default:
				return new ResponseEntity<Boolean>(false, HttpStatus.OK);
		}
	}

	@PostMapping(path = "/denyRegistrationRequest/{id}")
	public ResponseEntity<Boolean> denyCreationRequest(@PathVariable("id") Long id, RequestEntity<String> request)
			throws AddressException, UnsupportedEncodingException {
				
			// {"type":"BOAT_OWNER","reason":"test"}
			String splitArray[] = request.getBody().split(",");
			String typeArray[] = splitArray[0].split(":");
			String reasonArray[] = splitArray[1].split(":");
			String type = typeArray[1].replaceAll("\"", "");
			String reason = reasonArray[1].replaceAll("\"", "").replaceAll("}", "");
			switch (type) {		
				case "BOAT_OWNER":
					BoatOwner boatOwner = boatOwnerService.getById(id);
					mailService.SendMail(boatOwner.getEmail(), boatOwner.getName(),
							"Your account has been denied. \nThank you for using our services! \n\nSincerely, Adventure Time." + reason);
					requestForAdminService.delete(requestForAdminService.findByForTypeAndRequesterId(HeadEntityEnum.BOAT_OWNER, id));
					return new ResponseEntity<Boolean>(boatOwnerService.markDenied(id), HttpStatus.OK);
	
				case "COTTAGE_OWNER":
					CottageOwner cottageOwner = cottageOwnerService.getById(id);
					mailService.SendMail(cottageOwner.getEmail(), cottageOwner.getName(),
							"Your account has been denied. \nThank you for using our services! \n\nSincerely, Adventure Time." + reason);
					requestForAdminService.delete(requestForAdminService.findByForTypeAndRequesterId(HeadEntityEnum.COTTAGE_OWNER, id));
					return new ResponseEntity<Boolean>(cottageOwnerService.markDenied(id), HttpStatus.OK);
	
				case "FISHING_INSTRUCTOR":
					FishingInstructor fishingInstructor = fishingInstructorService.getById(id);
					mailService.SendMail(fishingInstructor.getEmail(), fishingInstructor.getName(),
							"Your account has been denied. \nThank you for using our services! \n\nSincerely, Adventure Time." + reason);
					requestForAdminService.delete(requestForAdminService.findByForTypeAndRequesterId(HeadEntityEnum.FISHING_INSTRUCTOR, id));
					return new ResponseEntity<Boolean>(fishingInstructorService.markDenied(id), HttpStatus.OK);

				default:
					return new ResponseEntity<Boolean>(false, HttpStatus.OK);
			}
	}
}