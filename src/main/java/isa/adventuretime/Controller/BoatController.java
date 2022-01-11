package isa.adventuretime.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import isa.adventuretime.Service.BoatService;
import java.util.Date;
import java.util.ArrayList;
import org.springframework.http.ResponseEntity;
import isa.adventuretime.Service.BoatBookingService;
import isa.adventuretime.Entity.Boat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping(path = "/api/boats")
public class BoatController {

	@Autowired
	private BoatService boatService;

	@Autowired
	private BoatBookingService boatBookingService;

	@GetMapping(path = "/get")
	public ResponseEntity<ArrayList<Boat>> getBoats() {
		return new ResponseEntity<ArrayList<Boat>>(boatService.findAll(), HttpStatus.OK);
	}

	@GetMapping(path = "/get/{id}")
	public ResponseEntity<Boat> getBoat(@PathVariable("id") Long id) {
		Boat boat = boatService.getById(id);
		if (boat == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Boat>(boat, HttpStatus.OK);
	}

	@PostMapping(path = "/getFreeTimes", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ArrayList<Date>> getAllFreeDates(RequestEntity<Long> id) {
		ArrayList<Date> dates = boatBookingService.getFreeTime(id.getBody());
		if (dates.size() == 0)
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		return new ResponseEntity<ArrayList<Date>>(dates, HttpStatus.OK);
	}

	@PostMapping(path = "/delete/{id}")
	public Boolean deleteBoat(@PathVariable("id") Long id) {
		Date now = new Date();

		if (boatBookingService.existsByBookedBoatIdAndEndAfter(id, now)) {
			return false;
		}

		boatService.deleteById(id);

		return true;
	}

	@PostMapping(path = "/update")
	public ResponseEntity<Boat> updateBoat(RequestEntity<Boat> boat) {
		Boat boatToSave = boat.getBody();
		boatToSave.setHidden(false);
		return new ResponseEntity<Boat>(boatService.save(boatToSave), HttpStatus.OK);
	}

	// TODO: Naziv ove metode nije konzistentan sa tim kako smo ih nazivali u npr
	// CottageController,
	// pa nisu ni URL path ni ime u repo-u isti.
	// U repo-u ima i 2 iste ove metode koje rade istu stvar.
	@GetMapping(path = "/getByOwner/{id}")
	public ResponseEntity<ArrayList<Boat>> getBoatsByOwner(@PathVariable("id") Long id) {
		return new ResponseEntity<ArrayList<Boat>>(boatService.findByOwnerId(id), HttpStatus.OK);
	}
}
