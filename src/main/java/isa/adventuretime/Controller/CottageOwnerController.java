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

import isa.adventuretime.DTO.CottageNameRoomBookingDTO;
import isa.adventuretime.Entity.Cottage;
import isa.adventuretime.Entity.CottageOwner;
import isa.adventuretime.Entity.RegisteredUser;
import isa.adventuretime.Entity.RoomBooking;
import isa.adventuretime.Service.CottageOwnerService;
import isa.adventuretime.Service.CottageService;
import isa.adventuretime.Service.RoomBookingService;

@RestController
@RequestMapping("/api/cottageOwner")
public class CottageOwnerController {

	@Autowired
	private CottageOwnerService cottageOwnerService;

	@Autowired
	private RoomBookingService roomBookingService;

	@Autowired
	private CottageService cottageService;


	@GetMapping("/get")
	public ResponseEntity<ArrayList<CottageOwner>> getAll() {
		return new ResponseEntity<ArrayList<CottageOwner>>(cottageOwnerService.getAll(), HttpStatus.OK);
	}

	@GetMapping("/get/{id}")
	public ResponseEntity<CottageOwner> getCottageOwner(@PathVariable("id") Long id) {
		return new ResponseEntity<>(cottageOwnerService.getById(id), HttpStatus.OK);
	}

	@GetMapping("/getByEmail/{emailHash}")
	public ResponseEntity<CottageOwner> getCottageOwnerByEmailHash(@PathVariable("emailHash") int emailHash) {
		CottageOwner cottageOwner = cottageOwnerService.findByEmailHash(emailHash);
		if (cottageOwner == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<>(cottageOwner, HttpStatus.OK);
		}
	}

	@GetMapping(path = "/pastRoomBookings/{ownerId}")
	public ResponseEntity<ArrayList<CottageNameRoomBookingDTO>> getPastRoomBookings(
			@PathVariable("ownerId") Long ownerId) throws Exception {
		ArrayList<Cottage> cottages = cottageService.findAllByOwnerId(ownerId);
		ArrayList<CottageNameRoomBookingDTO> cottagesDTO = new ArrayList<>();

		Date date = new Date();

		for (Cottage cottage : cottages) {
			ArrayList<RoomBooking> roomBookings = new ArrayList<>();
			roomBookings
					.addAll(roomBookingService.findAllByCottageIdAndEndBefore(cottage.getId(), date));

			if (roomBookings.size() > 0) {
				cottagesDTO.add(new CottageNameRoomBookingDTO(cottage.getName(), roomBookings));
			}
		}

		return new ResponseEntity<ArrayList<CottageNameRoomBookingDTO>>(cottagesDTO, HttpStatus.OK);
	}

	@GetMapping("/checkBooked/{id}")
	public ResponseEntity<Boolean> checkBooked(@PathVariable("id") Long id) {
		return new ResponseEntity<>(roomBookingService.existsByCottageId(id), HttpStatus.OK);
	}

	@PostMapping(path = "/delete/{id}")
	public ResponseEntity<Boolean> deleteCottageOwner(@PathVariable("id") Long id) {
		return new ResponseEntity<Boolean>(cottageOwnerService.markDeleted(id), HttpStatus.OK);
	}

	@GetMapping(path = "/acquireCustomer/{id}")
	public ResponseEntity<ArrayList<RegisteredUser>> acquireCustomer(@PathVariable("id") Long id){

		return null;
	}
}