package isa.adventuretime.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import isa.adventuretime.Entity.CottageOwner;
import isa.adventuretime.Service.CottageOwnerService;

@RestController
@RequestMapping("/api/cottageOwner")
public class CottageOwnerController {

	@Autowired
	private CottageOwnerService cottageOwnerService;

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
}