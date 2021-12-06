package isa.adventuretime.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import isa.adventuretime.Entity.BoatOwner;
import isa.adventuretime.Service.BoatOwnerService;

@RestController
@RequestMapping("/api/boatOwner")
public class BoatOwnerController {

	@Autowired
	private BoatOwnerService boatOwnerService;

	@GetMapping("/get/{id}")
	public ResponseEntity<BoatOwner> getBoatOwner(@PathVariable("id") Long id) {
		return new ResponseEntity<>(boatOwnerService.getOne(id), HttpStatus.OK);
	}
}