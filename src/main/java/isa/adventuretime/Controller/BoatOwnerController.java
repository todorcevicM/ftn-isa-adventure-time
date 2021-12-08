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
import isa.adventuretime.Entity.BoatOwner;
import isa.adventuretime.Service.BoatOwnerService;

@RestController
@RequestMapping("/api/boatOwner")
public class BoatOwnerController {

	@Autowired
	private BoatOwnerService boatOwnerService;

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
			return new ResponseEntity<>(boatOwner, HttpStatus.OK);
		}
	}

	@PostMapping(path = "/delete/{id}")
	public void deleteBoatOwner(@PathVariable("id") Long id) {
		boatOwnerService.deleteById(id);
	}
}