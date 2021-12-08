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

import isa.adventuretime.Entity.Cottage;
import isa.adventuretime.Entity.Room;
import isa.adventuretime.Service.CottageService;
import isa.adventuretime.Service.RoomBookingService;
import isa.adventuretime.Service.RoomService;

@RestController // Ili @Controller sa @ResponseBody, ne sme samo @Controller
@RequestMapping(path = "/api/cottages")
public class CottageController {

	@Autowired
	private CottageService cottageService;

	@Autowired
	private RoomService roomService;

	@Autowired
	private RoomBookingService roomBookingService;

	@GetMapping(path = "/get")
	public ResponseEntity<ArrayList<Cottage>> getCottages() {
		return new ResponseEntity<ArrayList<Cottage>>(cottageService.findAll(), HttpStatus.OK);
	}

	@GetMapping(path = "/get/{id}")
	public ResponseEntity<Cottage> getCottage(@PathVariable("id") Long id) {
		Cottage cottage = cottageService.getById(id);
		if (cottage == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Cottage>(cottage, HttpStatus.OK);
	}

	@GetMapping(path = "/getRoomsForCottage/{id}")
	public ResponseEntity<ArrayList<Room>> getRoomsForCottage(@PathVariable("id") Long id) {
		return new ResponseEntity<ArrayList<Room>>(roomService.findAllByCottageId(id), HttpStatus.OK);
	}

	// @PostMapping(path = "/save", consumes = MediaType.APPLICATION_JSON_VALUE,
	// produces = MediaType.APPLICATION_JSON_VALUE)
	// public ResponseEntity<Cottage> saveCottage(RequestEntity<Cottage> cottage) {
	// // TODO: Prosledi objekat, ne RequestEntity
	// return new ResponseEntity<Cottage>(cottageService.save(cottage),
	// HttpStatus.OK);
	// }

	@PostMapping(path = "/delete/{id}")
	public Boolean deleteCottage(@PathVariable("id") Long id) {
		Date now = new Date();

		if (roomBookingService.existsByCottageIdAndEndAfter(id, now)) {
			return false;
		}

		ArrayList<Room> rooms = roomService.getAllByCottageId(id);
		for (Room room : rooms) {
			roomService.deleteById(room.getId());
		}
		cottageService.deleteById(id);

		return true;
	}

	@GetMapping(path = "/getAllByOwnerId/{id}")
	public ResponseEntity<ArrayList<Cottage>> getAllByOwnerId(@PathVariable("id") Long id) {
		return new ResponseEntity<ArrayList<Cottage>>(cottageService.getAllByOwnerId(id), HttpStatus.OK);
	}

}
