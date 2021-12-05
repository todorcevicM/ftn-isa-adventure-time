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

@RestController // Ili @Controller sa @ResponseBody, ne sme samo @Controller
@RequestMapping(path = "/api/boat")
public class BoatController {

	@Autowired
	private BoatService boatService;

	@Autowired
	private BoatBookingService boatBookingService;
	/*
	 * @PostMapping(value = ("/login"), consumes = MediaType.APPLICATION_JSON_VALUE,
	 * produces = MediaType.APPLICATION_JSON_VALUE)
	 * public ResponseEntity<User> UserLogin(RequestEntity<ArrayList<String>>
	 * credentials) throws Exception {
	 */

	@PostMapping(path = "/getFreeTimes", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ArrayList<Date>> getAllFreeDates(RequestEntity<Long> id) {
		ArrayList<Date> dates = boatBookingService.getFreeTime(id.getBody());
		if (dates.size() == 0)
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		return new ResponseEntity<ArrayList<Date>>(dates, HttpStatus.OK);
	}

}
