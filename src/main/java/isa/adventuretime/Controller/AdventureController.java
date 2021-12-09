package isa.adventuretime.Controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import isa.adventuretime.Entity.Adventure;
import isa.adventuretime.Service.AdventureService;

import org.springframework.http.MediaType;

import java.util.Date;

import isa.adventuretime.Service.AdventureBookingService;

@RestController // Ili @Controller sa @ResponseBody, ne sme samo @Controller
@RequestMapping(path = "/api/adventures")
public class AdventureController {

	@Autowired
	private AdventureService adventureService;

	@Autowired
	private AdventureBookingService adventureBookingService;

	@GetMapping(path = "/get")
	public ResponseEntity<ArrayList<Adventure>> getAdventures() {
		return new ResponseEntity<ArrayList<Adventure>>(adventureService.findAll(), HttpStatus.OK);
	}

	@GetMapping(path = "/get/{id}")
	public ResponseEntity<Adventure> getAdventure(@PathVariable("id") Long id) {
		Adventure adventure = adventureService.getById(id);
		if (adventure == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Adventure>(adventure, HttpStatus.OK);
	}

	@PostMapping(path = "/getFreeTimes", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ArrayList<Date>> getAllFreeDates(RequestEntity<Long> id) {
		ArrayList<Date> dates = adventureBookingService.getFreeTime(id.getBody());
		if (dates.size() == 0)
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		return new ResponseEntity<ArrayList<Date>>(dates, HttpStatus.OK);
	}

	// @PostMapping(path = "/save", consumes = MediaType.APPLICATION_JSON_VALUE,
	// produces = MediaType.APPLICATION_JSON_VALUE)
	// public ResponseEntity<Adventure> saveAdventure(RequestEntity<Adventure>
	// adventure) {
	// // TODO: Prosledi objekat, ne RequestEntity
	// return new ResponseEntity<Adventure>(adventureService.save(adventure),
	// HttpStatus.OK);
	// }

	@PostMapping(path = "/delete/{id}")
	public Boolean deleteAdventure(@PathVariable("id") Long id) {
		Date now = new Date();

		if (adventureBookingService.existsByBookedInstructorIdAndEndAfter(id, now)) {
			return false;
		}

		adventureService.deleteById(id);

		return true;
	}

	@PostMapping(path = "/update")
	public ResponseEntity<Adventure> updateAdventure(RequestEntity<Adventure> adventure) {
		return new ResponseEntity<Adventure>(adventureService.save(adventure.getBody()), HttpStatus.OK);
	}

}
