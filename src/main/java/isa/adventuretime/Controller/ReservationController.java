package isa.adventuretime.Controller;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import isa.adventuretime.DTO.CottageWithRoomDTO;
import isa.adventuretime.Entity.Adventure;
import isa.adventuretime.Entity.Boat;
import isa.adventuretime.Service.AdventureService;
import isa.adventuretime.Service.BoatService;
import isa.adventuretime.Service.CottageService;

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
		if (split.length != 6) {
			System.out.println("User tried using ';' as part of his search");
			return null;
		}
		String name = split[0];
		String type = split[1];
		String dateAndTime = split[2];
		String time = split[3];
		String numberOfDays = split[4];
		String numberOfGuests = split[5];
		System.out.println(dateAndTime);
		Calendar date = Calendar.getInstance();
		date.set(	Integer.parseInt(dateAndTime.split("-")[0]), 
		Integer.parseInt(dateAndTime.split("-")[1]) -1, 
		Integer.parseInt(dateAndTime.split("-")[2]),
		Integer.parseInt(time.split(":")[0]),
		Integer.parseInt(time.split(":")[1]), 0);
		
		Date startDate = date.getTime();
		System.out.println(date.getTime());
		date.add(Calendar.DAY_OF_MONTH, Integer.parseInt(numberOfDays));
		System.out.println(date.getTime());
		Date endDate = date.getTime();


		//TODO: grade - Nikola
		switch (type) {
			case "Adventure":
				ArrayList<Adventure> adventures = adventureService.getAllBySearchQuery(name, startDate, endDate,Integer.parseInt(numberOfGuests), 0);
				if(adventures == null || adventures.isEmpty())
					return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
				return new ResponseEntity<>(new ArrayList<Object>(adventures), HttpStatus.OK);
			case "Boat":
				ArrayList<Boat> boats = boatService.getAllBySearchQuery(name, startDate, endDate,Integer.parseInt(numberOfGuests), 0);
				if(boats == null || boats.isEmpty())
					return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
				return new ResponseEntity<>(new ArrayList<Object>(boats), HttpStatus.OK);
			case "Cottage":
				ArrayList<CottageWithRoomDTO>  r =  cottageService.getAllBySearchQuery(name, startDate, endDate,Integer.parseInt(numberOfGuests), 0);
				if(r == null || r.isEmpty())
					return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
				return new ResponseEntity<>(new ArrayList<Object>(r), HttpStatus.OK);
			default:
			System.out.println("Something went wrong");
			break;
		}

		
		return null;
		
	}
	
}