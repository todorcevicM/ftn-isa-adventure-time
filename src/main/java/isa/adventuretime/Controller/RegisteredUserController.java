package isa.adventuretime.Controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import isa.adventuretime.Entity.BoatBooking;
import isa.adventuretime.Service.BoatBookingService;
import isa.adventuretime.Service.RegisteredUserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import isa.adventuretime.Service.AdventureBookingService;
import isa.adventuretime.Entity.RoomBooking;
import isa.adventuretime.Service.RoomBookingService;
import isa.adventuretime.Entity.AdventureBooking;

@RestController
@RequestMapping(path = "/api/registeredUser")
public class RegisteredUserController {
	@Autowired
	private RegisteredUserService registeredUserService;

	@Autowired
	private BoatBookingService boatBookingService;

	@Autowired
	private RoomBookingService roomBookService;

	@Autowired
	private AdventureBookingService adventureBookingService;

	@GetMapping(path = "/pastBoatBookings/{id}")
	public ResponseEntity<ArrayList<BoatBooking>> getBoatBookings(@PathVariable("id") Long id) throws Exception {
		return new ResponseEntity<ArrayList<BoatBooking>>(
				boatBookingService.findAllByRegisteredUserIdAndEndBefore(id, new java.util.Date()), HttpStatus.OK);
	}

	@GetMapping(path = "/pastAdventureBooking/{id}")
	public ResponseEntity<ArrayList<AdventureBooking>> getAdventureBookings(@PathVariable("id") Long id) {
		return new ResponseEntity<ArrayList<AdventureBooking>>(
				adventureBookingService.findAllByRegisteredUserIdAndEndBefore(id, new java.util.Date()), HttpStatus.OK);
	}

	@GetMapping(path = "/pastRoomBooking/{id}")
	public ResponseEntity<ArrayList<RoomBooking>> getRoomBookings(@PathVariable("id") Long id) throws Exception {
		return new ResponseEntity<ArrayList<RoomBooking>>(
				roomBookService.findAllByRegisteredUserIdAndEndBefore(id, new java.util.Date()), HttpStatus.OK);
	}

}