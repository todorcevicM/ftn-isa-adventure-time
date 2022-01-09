package isa.adventuretime.Controller;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import javax.mail.internet.AddressException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import isa.adventuretime.DTO.CottageWithRoomDTO;
import isa.adventuretime.Entity.Adventure;
import isa.adventuretime.Entity.AdventureBooking;
import isa.adventuretime.Entity.Boat;
import isa.adventuretime.Entity.BoatBooking;
import isa.adventuretime.Entity.Room;
import isa.adventuretime.Entity.RoomBooking;
import isa.adventuretime.Service.AdventureBookingService;
import isa.adventuretime.Service.AdventureService;
import isa.adventuretime.Service.BoatBookingService;
import isa.adventuretime.Service.BoatService;
import isa.adventuretime.Service.CottageService;
import isa.adventuretime.Service.FishingInstructorService;
import isa.adventuretime.Service.MailService;
import isa.adventuretime.Service.RegisteredUserService;
import isa.adventuretime.Service.RoomBookingService;

@RestController
@RequestMapping("/api/booking")
public class BookingController {

	@Autowired
	AdventureBookingService adventureBookingService;

	@Autowired
	BoatBookingService boatBookingService;

	@Autowired
	RoomBookingService roomBookingService;

	@Autowired
	CottageService cottageService;
	@Autowired
	AdventureService adventureService;
	@Autowired
	BoatService boatService;

	@Autowired
	FishingInstructorService fishingInstructorService;

	@Autowired
	MailService mailService;
	@Autowired
	RegisteredUserService registeredUserService;


	@PostMapping(path = "/quickBoatBooking")
	public Boolean quickBoatBooking(RequestEntity<String> params) {
		String[] paramsArray = params.getBody().split(",");
		Long boatBookingId = Long.parseLong(paramsArray[0].split(":")[1]);
		Long userId = Long.parseLong(paramsArray[1].split(":")[1].replace("}", ""));
		BoatBooking bb = boatBookingService.getById(boatBookingId);

		bb.setRegisteredUserId(userId);
		return boatBookingService.save(bb) != null;
	}

	@PostMapping(path = "/quickAdventureBooking")
	public Boolean quickAdventureBooking(RequestEntity<String> params) {
		String[] paramsArray = params.getBody().split(",");
		Long adventureBookingId = Long.parseLong(paramsArray[0].split(":")[1]);
		Long userId = Long.parseLong(paramsArray[1].split(":")[1].replace("}", ""));
		AdventureBooking ab = adventureBookingService.getById(adventureBookingId);

		ab.setRegisteredUserId(userId);
		return adventureBookingService.save(ab) != null;
	}

	@PostMapping(path = "/quickCottageBooking")
	public Boolean quickCottageBooking(RequestEntity<String> params) {
		String[] paramsArray = params.getBody().split(",");
		Long roomBookingId = Long.parseLong(paramsArray[0].split(":")[1]);
		Long userId = Long.parseLong(paramsArray[1].split(":")[1].replace("}", ""));
		RoomBooking rb = roomBookingService.getById(roomBookingId);

		rb.setRegisteredUserId(userId);
		return roomBookingService.save(rb) != null;
	}

	@PostMapping(path = "/room")
	public Boolean bookRoom(RequestEntity<String> param) throws AddressException, UnsupportedEncodingException {
		System.out.println(param.getBody());
		String params[] = param.getBody().split(",");
		Long cottageId = Long.parseLong(params[0].split(":")[1]);
		String dateD = params[1].split(":")[1].replace("\"", "");
		String time = params[2].split("\"")[3];
		Calendar date = Calendar.getInstance();
		date.set(
				Integer.parseInt(dateD.split("-")[0]),
				Integer.parseInt(dateD.split("-")[1]) - 1,
				Integer.parseInt(dateD.split("-")[2]),
				Integer.parseInt(time.split(":")[0]),
				Integer.parseInt(time.split(":")[1]), 0);
		int days = Integer.parseInt(params[3].split(":")[1]);
		int guests = Integer.parseInt(params[4].split(":")[1]);
		Long ownerId = Long.parseLong(params[5].split(":")[1].replace("\"", "").replace("}", ""));
		// Long userId = Long.parseLong(params[6].split(":")[1].replace("\"",
		// "").replace("}", ""));
		// TODO: ovde fali od userId sa fronta
		Long userId = 1L;

		Date startDate = date.getTime();
		date.add(Calendar.DAY_OF_MONTH, days);
		Date endDate = date.getTime();

		String cottageName = cottageService.getById(cottageId).getName();

		ArrayList<CottageWithRoomDTO> rooms = cottageService.getAllBySearchQuery(cottageName, startDate, endDate,
				guests);
		if (rooms == null || rooms.isEmpty())
			return false;

		CottageWithRoomDTO room = rooms.get(0);
		Room r = room.getRoom();
		RoomBooking roomBooking = new RoomBooking(
				r.getId(),
				userId,
				startDate,
				endDate,
				"extraService",
				guests,
				rooms.get(0).getCottage().getId());

		String userName = registeredUserService.getById(userId).getName();
		String userEmail = registeredUserService.getById(userId).getEmail();

		mailService.SendMail(userEmail, userName,
				"Rezervacija sobe \n\n Vlasnik je uspesno rezervisao sobu u Vase ime u kući: " + cottageName
						+ " u periodu od " + startDate + " do " + endDate + ".");

		return roomBookingService.save(roomBooking) != null;
	}

	@PostMapping(path = "/boat")
	public Boolean bookBoat(RequestEntity<String> param) throws AddressException, UnsupportedEncodingException {
		System.out.println(param.getBody());
		String params[] = param.getBody().split(",");
		Long boatId = Long.parseLong(params[0].split(":")[1]);
		String dateD = params[1].split(":")[1].replace("\"", "");
		String time = params[2].split("\"")[3];
		Calendar date = Calendar.getInstance();
		date.set(
				Integer.parseInt(dateD.split("-")[0]),
				Integer.parseInt(dateD.split("-")[1]) - 1,
				Integer.parseInt(dateD.split("-")[2]),
				Integer.parseInt(time.split(":")[0]),
				Integer.parseInt(time.split(":")[1]), 
				0
		);
		int days = Integer.parseInt(params[3].split(":")[1]);
		int guests = Integer.parseInt(params[4].split(":")[1]);
		Long ownerId = Long.parseLong(params[5].split(":")[1].replace("\"", "").replace("}", ""));
		// Long userId = Long.parseLong(params[6].split(":")[1].replace("\"",
		// "").replace("}", ""));
		// TODO: ovde fali od userId sa fronta
		Long userId = 1L;

		Date startDate = date.getTime();
		date.add(Calendar.DAY_OF_MONTH, days);
		Date endDate = date.getTime();

		String boatName = boatService.getById(boatId).getName();

		ArrayList<Boat> boats = boatService.getAllBySearchQuery(boatName, startDate, endDate, guests);
		BoatBooking boatBooking = new BoatBooking(
				boats.get(0).getId(),
				userId,
				startDate,
				endDate,
				"extraService",
				guests);

		String userName = registeredUserService.getById(userId).getName();
		String userEmail = registeredUserService.getById(userId).getEmail();

		mailService.SendMail(userEmail, userName,
				"Rezervacija broda \n\n Vlasnik je uspesno rezervisao brod u Vase ime u kući: " + boatName
						+ " u periodu od " + startDate + " do " + endDate + ".");

		BoatBooking bb = boatBookingService.save(boatBooking);
		return bb != null;
	}

	@PostMapping(path = "/adventure")
	public Boolean bookAdventure(RequestEntity<String> param) throws AddressException, UnsupportedEncodingException {
		System.out.println(param.getBody());
		String params[] = param.getBody().split(",");
		Long adventureId = Long.parseLong(params[0].split(":")[1]);
		String dateD = params[1].split(":")[1].replace("\"", "");
		String time = params[2].split("\"")[3];
		Calendar date = Calendar.getInstance();
		date.set(
				Integer.parseInt(dateD.split("-")[0]),
				Integer.parseInt(dateD.split("-")[1]) - 1,
				Integer.parseInt(dateD.split("-")[2]),
				Integer.parseInt(time.split(":")[0]),
				Integer.parseInt(time.split(":")[1]), 0);
		int days = Integer.parseInt(params[3].split(":")[1]);
		int guests = Integer.parseInt(params[4].split(":")[1]);
		Long instructorId = Long.parseLong(params[5].split(":")[1].replace("\"", "").replace("}", ""));
		// Long ownerId = Long.parseLong(params[5].split(":")[1].replace("\"",
		// "").replace("}", ""));
		// Long userId = Long.parseLong(params[6].split(":")[1].replace("\"",
		// "").replace("}", ""));
		// TODO: ovde fali od userId sa fronta
		Long userId = 1L;

		Date startDate = date.getTime();
		date.add(Calendar.DAY_OF_MONTH, days);
		Date endDate = date.getTime();

		String adventureName = adventureService.getById(adventureId).getName();
		ArrayList<Adventure> adventures = adventureService.getAllBySearchQuery(adventureName, startDate, endDate,
				guests);

		AdventureBooking adventureBooking = new AdventureBooking(
				adventures.get(0).getId(),
				instructorId,
				userId,
				startDate,
				endDate,
				"extraService",
				guests);

		String userName = registeredUserService.getById(userId).getName();
		String userEmail = registeredUserService.getById(userId).getEmail();

		mailService.SendMail(userEmail, userName,
				"Rezervacija avanture \n\n Instruktor je uspesno rezervisao avanturu u Vase ime u kući: "
						+ adventureName + " u periodu od " + startDate + " do " + endDate + ".");

		AdventureBooking ab = adventureBookingService.save(adventureBooking);
		return ab != null;
	}

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
		date.set(Integer.parseInt(dateAndTime.split("-")[0]),
				Integer.parseInt(dateAndTime.split("-")[1]) - 1,
				Integer.parseInt(dateAndTime.split("-")[2]),
				Integer.parseInt(time.split(":")[0]),
				Integer.parseInt(time.split(":")[1]), 0);

		Date startDate = date.getTime();
		System.out.println(date.getTime());
		date.add(Calendar.DAY_OF_MONTH, Integer.parseInt(numberOfDays));
		System.out.println(date.getTime());
		Date endDate = date.getTime();

		switch (type) {
			case "Adventure":
				ArrayList<Adventure> adventures = adventureService.getAllBySearchQuery(name, startDate, endDate,
						Integer.parseInt(numberOfGuests));
				if (adventures == null || adventures.isEmpty())
					return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
				return new ResponseEntity<>(new ArrayList<Object>(adventures), HttpStatus.OK);
			case "Boat":
				ArrayList<Boat> boats = boatService.getAllBySearchQuery(name, startDate, endDate,
						Integer.parseInt(numberOfGuests));
				if (boats == null || boats.isEmpty())
					return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
				return new ResponseEntity<>(new ArrayList<Object>(boats), HttpStatus.OK);
			case "Cottage":
				ArrayList<CottageWithRoomDTO> rooms = cottageService.getAllBySearchQuery(name, startDate, endDate,
						Integer.parseInt(numberOfGuests));
				if (rooms == null || rooms.isEmpty())
					return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
				return new ResponseEntity<>(new ArrayList<Object>(rooms), HttpStatus.OK);
			default:
				System.out.println("Something went wrong");
				return null;
		}

	}

	@GetMapping(path = "/boatBookingDeal/{id}")
	public ResponseEntity<ArrayList<BoatBooking>> boatBookingDeal(@PathVariable("id") Long id) {
		return new ResponseEntity<>(
				boatBookingService.findAllByBookedBoatIdAndStartAfterAndQuickBookingAndRegisteredUserId(id),
				HttpStatus.OK);
	}

	@GetMapping(path = "/cottageBookingDeal/{id}")
	public ResponseEntity<ArrayList<RoomBooking>> cottageBookingDeal(@PathVariable("id") Long id) {
		return new ResponseEntity<>(
				roomBookingService.findAllByBookedRoomIdAndStartAfterAndQuickBookingAndRegisteredUserId(id),
				HttpStatus.OK);
	}

	@GetMapping(path = "/adventureBookingDeal/{id}")
	public ResponseEntity<ArrayList<AdventureBooking>> adventureBookingDeal(@PathVariable("id") Long id) {
		return new ResponseEntity<>(
				adventureBookingService.findAllByBookedAdventureIdAndStartAfterAndQuickBookingAndRegisteredUserId(id),
				HttpStatus.OK);
	}

	@PostMapping(path = "/cancelBooking")
	public Boolean cancleBooking(RequestEntity<String> bookingParam) {
		String split[] = bookingParam.getBody().split(",");
		Long id = Long.parseLong(split[0].split("\"")[2].split(":")[1]);
		String forType = split[1].split("\"")[3];

		Calendar date = Calendar.getInstance();
		date.add(Calendar.DAY_OF_MONTH, 3);

		switch (forType) {
			case "ADVENTURE":
				AdventureBooking ab = adventureBookingService.getById(id);
				if (ab != null && ab.getStart().after(date.getTime())) {
					if (ab.getQuickBooking()) {
						ab.setRegisteredUserId(0L);
						adventureBookingService.save(ab);
						return true;
					} else {
						adventureBookingService.delete(ab);
						return true;
					}
				}
				return false;
			case "BOAT":
				BoatBooking bb = boatBookingService.getById(id);
				if (bb != null && bb.getStart().after(date.getTime())) {
					if (bb.getQuickBooking()) {
						bb.setRegisteredUserId(0L);
						boatBookingService.save(bb);
						return true;
					} else {
						boatBookingService.delete(bb);
						return true;
					}
				}
				return false;
			case "COTTAGE":
				RoomBooking rb = roomBookingService.getById(id);
				if (rb != null && rb.getStart().after(date.getTime())) {
					if (rb.getQuickBooking()) {
						rb.setRegisteredUserId(0L);
						roomBookingService.save(rb);
						return true;
					} else {
						roomBookingService.delete(rb);
						return true;
					}
				}
				return false;
			default:
				System.err.println("Something went wrong >>> " + forType);
				return false;
		}
	}

}
