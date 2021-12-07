package isa.adventuretime.Controller;

import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import isa.adventuretime.Entity.Cottage;
import isa.adventuretime.Service.*;

import org.springframework.web.bind.annotation.PathVariable;

@RestController // Ili @Controller sa @ResponseBody, ne sme samo @Controller
@RequestMapping(path = "/api/cottages")
public class CottageController {

	@Autowired
	private CottageService cottageService;

	@Autowired
	private RoomService roomService;

	@GetMapping(path = "/get")
	public ResponseEntity<ArrayList<Cottage>> getCottages() {
		return new ResponseEntity<ArrayList<Cottage>>(cottageService.findAll(), HttpStatus.OK);
	}

	@GetMapping(path = "/get/{id}")
	public ResponseEntity<Cottage> getAdventure(@PathVariable("id") Long id) {
		Cottage cottage = cottageService.getById(id);
		if (cottage == null) {
			// TODO: get all rooms
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<Cottage>(cottage, HttpStatus.OK);
	}
}
