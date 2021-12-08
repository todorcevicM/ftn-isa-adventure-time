package isa.adventuretime.Controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import isa.adventuretime.DTO.AdventureNameAdventureBookingDTO;
import isa.adventuretime.Entity.FishingInstructor;
import isa.adventuretime.Service.AdventureBookingService;
import isa.adventuretime.Service.AdventureService;
import isa.adventuretime.Service.FishingInstructorService;
import isa.adventuretime.Entity.Adventure;
import isa.adventuretime.Entity.AdventureBooking;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/api/fishingInstructor")
public class FishingInstructorController {

	@Autowired
	private FishingInstructorService fishingInstructorService;

	@Autowired 
	private AdventureService adventureService;

	@Autowired
	private AdventureBookingService adventureBookingService;

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
	public ResponseEntity<ArrayList<AdventureNameAdventureBookingDTO>> getPastAdventureBookings(@PathVariable("instructiorId") Long instructiorId) {
		ArrayList<Adventure> adventures = adventureService.getPastAdventuresByInstructorId(instructiorId);
		ArrayList<AdventureNameAdventureBookingDTO> adventureNameAdventureBookingDTOs = new ArrayList<>();

		for (Adventure adventure : adventures) {
			ArrayList<AdventureBooking> adventureBookings = new ArrayList<>(); 
			adventureBookings.addAll(adventureBookingService.findAllByBookedAdventureIdAndEndBefore(adventure.getId(), new java.util.Date()));
			
			if (adventureBookings.size() > 0) {
				adventureNameAdventureBookingDTOs.add(new AdventureNameAdventureBookingDTO(adventure.getName(), adventureBookings));
			}
		}

		return new ResponseEntity<>(adventureNameAdventureBookingDTOs, HttpStatus.OK);
	}

	@PostMapping(path = "/delete/{id}")
	public void deleteFishingInstructor(@PathVariable("id") Long id) {
		fishingInstructorService.deleteById(id);
	}
	
}