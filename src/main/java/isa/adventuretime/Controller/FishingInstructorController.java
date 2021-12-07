package isa.adventuretime.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import isa.adventuretime.Entity.FishingInstructor;
import isa.adventuretime.Service.FishingInstructorService;

@RestController
@RequestMapping("/api/fishingInstructor")
public class FishingInstructorController {

	@Autowired
	private FishingInstructorService fishingInstructorService;

	@GetMapping("/get/{id}")
	public ResponseEntity<FishingInstructor> getFishingInstructor(@PathVariable("id") Long id) {
		return new ResponseEntity<>(fishingInstructorService.getById(id), HttpStatus.OK);
	}

	@GetMapping(value = ("/getByEmail/{emailHash}"))
	public ResponseEntity<FishingInstructor> getFishingInstructorByEmailHash(@PathVariable("emailHash") int emailHash) {
		FishingInstructor fishingInstructor = fishingInstructorService.findByEmailHash(emailHash);
		if (fishingInstructor == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<>(fishingInstructor, HttpStatus.OK);
		}
	}
}