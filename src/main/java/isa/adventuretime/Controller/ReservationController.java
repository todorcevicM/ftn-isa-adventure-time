package isa.adventuretime.Controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import java.util.ArrayList;
import org.springframework.http.ResponseEntity;
import org.springframework.http.RequestEntity;
import org.springframework.web.bind.annotation.PostMapping;
import isa.adventuretime.Service.AdventureService;
import isa.adventuretime.Service.CottageService;
import isa.adventuretime.Service.BoatService;
import org.springframework.beans.factory.annotation.Autowired;

@RestController
@RequestMapping("/api/reservation")
public class ReservationController {

	@Autowired
	BoatService boatService;

	@Autowired
	CottageService cottageService;

	@Autowired
	AdventureService adventureService;

	@PostMapping(path = "/search")
	public ResponseEntity<ArrayList<Object>> getReservableBoats(RequestEntity<String> searchParam) {
		String query = searchParam.getBody();
		// Trim-ovanje " na pocetku i kraju
		query = query.substring(1, query.length() - 1);
		System.out.println(query);
		String split[] = query.split(";");
		if (split.length != 5) {
			System.out.println("User tried using ';' as part of his search");
			return null;
		}
		String name = split[0];
		// TODO: Ovde ti ja msm fali tip po kojem se search-uje,
		// pa ce ti gore biti if != 6
		String dateAndTime = split[1];
		String numberOfDays = split[2];
		String numberOfGuests = split[3];
		String score = split[4];
		System.out.println(name);
		System.out.println(dateAndTime);
		System.out.println(numberOfDays);
		System.out.println(numberOfGuests);
		System.out.println(score);
		return null;
	}

}