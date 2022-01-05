package isa.adventuretime.Controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import isa.adventuretime.DTO.BoatNameBoatBookingDTO;
import isa.adventuretime.Entity.Boat;
import isa.adventuretime.Entity.BoatBooking;
import isa.adventuretime.Entity.BoatOwner;
import isa.adventuretime.Service.BoatBookingService;
import isa.adventuretime.Service.BoatOwnerService;
import isa.adventuretime.Service.BoatService;

@RestController
@RequestMapping("/api/boatOwner")
public class BoatOwnerController {

	@Autowired
	private BoatOwnerService boatOwnerService;

	@Autowired
	private BoatBookingService boatBookingService;

	@Autowired
	private BoatService boatService;


	@GetMapping("/get")
	public ResponseEntity<ArrayList<BoatOwner>> getAll() {
		return new ResponseEntity<>(boatOwnerService.getAll(), HttpStatus.OK);
	}

	@GetMapping("/get/{id}")
	public ResponseEntity<BoatOwner> getBoatOwner(@PathVariable("id") Long id) {
		return new ResponseEntity<>(boatOwnerService.getById(id), HttpStatus.OK);
	}

	@GetMapping(value = ("/getByEmail/{emailHash}"))
	public ResponseEntity<BoatOwner> getBoatOwnerByEmailHash(@PathVariable("emailHash") int emailHash) {
		BoatOwner boatOwner = boatOwnerService.findByEmailHash(emailHash);
		if (boatOwner == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} else {
			System.out.println(boatOwner.getName());
			return new ResponseEntity<>(boatOwner, HttpStatus.OK);
		}
	}

	@PostMapping(path = "/delete/{id}")
	public ResponseEntity<Boolean> deleteBoatOwner(@PathVariable("id") Long id) {
		return new ResponseEntity<Boolean>(boatOwnerService.markDeleted(id), HttpStatus.OK);
	}

	@GetMapping(path = "/pastBoatBookings/{boatOwnerId}")
	public ResponseEntity<ArrayList<BoatNameBoatBookingDTO>> getPastBoatBookings(
			@PathVariable("boatOwnerId") Long boatOwnerId) {
		ArrayList<Boat> boats = boatService.getPastBoatsByOwnerId(boatOwnerId);
		ArrayList<BoatNameBoatBookingDTO> boatNameBoatBookingDTOs = new ArrayList<>();

		for (Boat boat : boats) {
			ArrayList<BoatBooking> boatBookings = new ArrayList<>();
			boatBookings.addAll(boatBookingService.findAllByBookedBoatIdAndEndBefore(boat.getId(), new java.util.Date()));

			if (boatBookings.size() > 0) {
				boatNameBoatBookingDTOs.add(new BoatNameBoatBookingDTO(boat.getName(), boatBookings));
			}
		}

		return new ResponseEntity<>(boatNameBoatBookingDTOs, HttpStatus.OK);
	}
}