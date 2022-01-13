package isa.adventuretime.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import isa.adventuretime.Entity.BoatOwner;
import isa.adventuretime.Entity.CottageOwner;
import isa.adventuretime.Entity.FishingInstructor;
import isa.adventuretime.Entity.RegisteredUser;
import isa.adventuretime.Service.BoatOwnerService;
import isa.adventuretime.Service.CottageOwnerService;
import isa.adventuretime.Service.FishingInstructorService;
import isa.adventuretime.Service.RegisteredUserService;
import isa.adventuretime.Service.BusinessConstantsService;
import isa.adventuretime.Entity.BusinessConstants;

@RestController
@RequestMapping(path = "/api")
public class MainController {

	@Autowired
	private RegisteredUserService registeredUserService;
	@Autowired
	private CottageOwnerService cottageOwnerService;
	@Autowired
	private BoatOwnerService boatOwnerService;
	@Autowired
	private FishingInstructorService fishingInstructorService;
	@Autowired
	BusinessConstantsService businessConstantsService;

	public MainController() {
	}

	@GetMapping(path = "/conf/{code}")
	public void confirmationMail(@PathVariable int code) {
		RegisteredUser registeredUser = registeredUserService.findByEmailHash(code);
		if (registeredUser != null) {
			registeredUser.setAuthenticated(true);
			registeredUserService.register(registeredUser);
			return;
		}
		CottageOwner cottageOwner = cottageOwnerService.findByEmailHash(code);
		if (cottageOwner != null) {
			cottageOwner.setAuthenticated(true);
			cottageOwnerService.register(cottageOwner);
			return;
		}
		BoatOwner boatOwner = boatOwnerService.findByEmailHash(code);
		if (boatOwner != null) {
			boatOwner.setAuthenticated(true);
			boatOwnerService.register(boatOwner);
			return;
		}
		FishingInstructor fishingInstructor = fishingInstructorService.findByEmailHash(code);
		if (fishingInstructor != null) {
			fishingInstructor.setAuthenticated(true);
			fishingInstructorService.register(fishingInstructor);
			return;
		}
		System.out.println("No such confirmation code was found: " + code);
	}

	@GetMapping(path = "/get/constant/{name}")
	public BusinessConstants getBusinessConstants(@PathVariable("name") String name) {
		return businessConstantsService.getBusinessConstantsByName(name);
	}

	@PostMapping(path = "/update/constant/{name}/{percent}")
	public ResponseEntity<BusinessConstants> updatePercent(@PathVariable("name") String name,
			@PathVariable("percent") double percent) {
		BusinessConstants bc = businessConstantsService.getBusinessConstantsByName(name);
		bc.setValue(percent);
		bc = businessConstantsService.save(bc);
		return new ResponseEntity<>(bc, HttpStatus.OK);
	}

}