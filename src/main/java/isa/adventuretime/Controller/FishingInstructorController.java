package isa.adventuretime.Controller;

import java.util.ArrayList;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import isa.adventuretime.DTO.AdventureNameAdventureBookingDTO;
import isa.adventuretime.DTO.UserNameAdventureBookingDTO;
import isa.adventuretime.Entity.Adventure;
import isa.adventuretime.Entity.AdventureBooking;
import isa.adventuretime.Entity.FishingInstructor;
import isa.adventuretime.Entity.RegisteredUser;
import isa.adventuretime.Service.AdventureBookingService;
import isa.adventuretime.Service.AdventureService;
import isa.adventuretime.Service.FishingInstructorService;
import isa.adventuretime.Service.RegisteredUserService;

@RestController
@RequestMapping("/api/fishingInstructor")
public class FishingInstructorController {

	@Autowired
	private FishingInstructorService fishingInstructorService;

	@Autowired
	private AdventureService adventureService;

	@Autowired
	private AdventureBookingService adventureBookingService;

	@Autowired
	private RegisteredUserService registeredUserService;

	@GetMapping(value = "/profitYear/{id}")
	public float reportProfitYear(@PathVariable("id") Long id){
		return fishingInstructorService.reportProfitYear(id);
	}

	@GetMapping(value = "/profitMonth/{id}")
	public float reportProfitMonth(@PathVariable("id") Long id){
		return fishingInstructorService.reportProfitMonth(id);
	}

	@GetMapping(value = "/profitWeek/{id}")
	public float reportProfitWeek(@PathVariable("id") Long id){
		return fishingInstructorService.reportProfitWeek(id);
	}


	@GetMapping(path = "/get")
	public ResponseEntity<ArrayList<FishingInstructor>> getAll() {
		return new ResponseEntity<>(fishingInstructorService.getAll(), HttpStatus.OK);
	}

	@GetMapping("/get/{id}")
	public ResponseEntity<FishingInstructor> getFishingInstructor(@PathVariable("id") Long id) {
		return new ResponseEntity<>(fishingInstructorService.getById(id), HttpStatus.OK);
	}

	@GetMapping(value = ("/getByEmail/{emailHash}"))
	public ResponseEntity<FishingInstructor> getFishingInstructorByEmailHash(@PathVariable("emailHash") int emailHash) {
		FishingInstructor fishingInstructor = fishingInstructorService.findByEmailHash(emailHash);
		if (fishingInstructor == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<>(fishingInstructor, HttpStatus.OK);
		}
	}

	@GetMapping(path = "/pastAdventureBookings/{instructiorId}")
	public ResponseEntity<ArrayList<AdventureNameAdventureBookingDTO>> getPastAdventureBookings(
			@PathVariable("instructiorId") Long instructiorId) {
		ArrayList<Adventure> adventures = adventureService.getPastAdventuresByInstructorId(instructiorId);
		ArrayList<AdventureNameAdventureBookingDTO> adventureNameAdventureBookingDTOs = new ArrayList<>();

		Date date = new Date();

		for (Adventure adventure : adventures) {
			ArrayList<AdventureBooking> adventureBookings = new ArrayList<>();
			ArrayList<UserNameAdventureBookingDTO> userNameAdventureBookingDTOs = new ArrayList<>();
			adventureBookings.addAll(adventureBookingService.findAllByBookedAdventureIdAndEndBefore(adventure.getId(),
					date));

			for (AdventureBooking adventureBooking : adventureBookings) {
				String userName = registeredUserService.getById(adventureBooking.getRegisteredUserId()).getName();
				userNameAdventureBookingDTOs.add(new UserNameAdventureBookingDTO(userName, adventureBooking));
			}

			if (adventureBookings.size() > 0) {
				adventureNameAdventureBookingDTOs
						.add(new AdventureNameAdventureBookingDTO(adventure.getName(), userNameAdventureBookingDTOs));
			}
		}

		return new ResponseEntity<>(adventureNameAdventureBookingDTOs, HttpStatus.OK);
	}

	@PostMapping(path = "/delete/{id}")
	public void deleteFishingInstructor(@PathVariable("id") Long id) {
		fishingInstructorService.markDeleted(id);
	}

	@GetMapping(path = "/currentCustomers/{id}")
	public ResponseEntity<ArrayList<AdventureNameAdventureBookingDTO>> acquireCustomer(@PathVariable("id") Long id) {
		ArrayList<RegisteredUser> registeredUsers = registeredUserService.getAllUsersOfFishingInstructor(id);
		ArrayList<AdventureNameAdventureBookingDTO> adventureNameAdventureBookingDTOs = new ArrayList<>();
		ArrayList<Adventure> adventures = adventureService.findByInstructorId(id);
		Date now = new Date();

		for (Adventure adventure : adventures) {
			ArrayList<UserNameAdventureBookingDTO> userNameAdventureBookingDTOs = new ArrayList<>();
			for (RegisteredUser registeredUser : registeredUsers) {
				String userNameUserlastname = registeredUser.getName() + " " + registeredUser.getLastname();
				ArrayList<AdventureBooking> adventureBookings = adventureBookingService
						.findAllByBookedAdventureIdAndStartBeforeAndEndAfter(adventure.getId(), now, now);
					
				for (AdventureBooking adventureBooking : adventureBookings) {
					if (adventureBooking.getBookedAdventureId() == adventure.getId()) {
						userNameAdventureBookingDTOs.add(new UserNameAdventureBookingDTO(userNameUserlastname,
								adventureBooking));
					}
				}
			}
			if (userNameAdventureBookingDTOs.size() > 0) {
				adventureNameAdventureBookingDTOs.add(new AdventureNameAdventureBookingDTO(adventure.getName(),
						userNameAdventureBookingDTOs));
			}
		}

		return new ResponseEntity<>(adventureNameAdventureBookingDTOs, HttpStatus.OK);
	}

}