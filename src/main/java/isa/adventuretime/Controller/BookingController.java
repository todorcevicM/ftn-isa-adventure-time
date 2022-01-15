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
import org.springframework.web.bind.annotation.RestController;

import isa.adventuretime.DTO.CottageWithRoomDTO;
import isa.adventuretime.Entity.Adventure;
import isa.adventuretime.Entity.AdventureBooking;
import isa.adventuretime.Entity.Boat;
import isa.adventuretime.Entity.BoatBooking;
import isa.adventuretime.Entity.Cottage;
import isa.adventuretime.Entity.HeadEntityEnum;
import isa.adventuretime.Entity.RegisteredUser;
import isa.adventuretime.Entity.Room;
import isa.adventuretime.Entity.RoomBooking;
import isa.adventuretime.Entity.Subscription;
import isa.adventuretime.Service.AdventureBookingService;
import isa.adventuretime.Service.AdventureService;
import isa.adventuretime.Service.BoatBookingService;
import isa.adventuretime.Service.BoatService;
import isa.adventuretime.Service.CottageService;
import isa.adventuretime.Service.FishingInstructorService;
import isa.adventuretime.Service.MailService;
import isa.adventuretime.Service.RegisteredUserService;
import isa.adventuretime.Service.RoomBookingService;
import isa.adventuretime.Service.RoomService;
import isa.adventuretime.Service.SubscriptionService;

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
	@Autowired
	RoomService roomService;
	@Autowired
	SubscriptionService subscriptionService;

	@PostMapping(path = "/quickBoatBooking")
	public Boolean quickBoatBooking(RequestEntity<String> params)
			throws AddressException, UnsupportedEncodingException {
		String[] paramsArray = params.getBody().split(",");
		Long boatBookingId = Long.parseLong(paramsArray[0].split(":")[1]);
		Long userId = Long.parseLong(paramsArray[1].split(":")[1].replace("}", ""));
		BoatBooking bb = boatBookingService.getById(boatBookingId);

		String userName = registeredUserService.getById(userId).getName();
		String userEmail = registeredUserService.getById(userId).getEmail();
		mailService.SendMail(userEmail, userName, "Boat booking.\n\n You have successfully booked a boat!",
				"Reservation");

		bb.setRegisteredUserId(userId);
		return boatBookingService.save(bb) != null;
	}

	@PostMapping(path = "/quickAdventureBooking")
	public Boolean quickAdventureBooking(RequestEntity<String> params)
			throws AddressException, UnsupportedEncodingException {
		String[] paramsArray = params.getBody().split(",");
		Long adventureBookingId = Long.parseLong(paramsArray[0].split(":")[1]);
		Long userId = Long.parseLong(paramsArray[1].split(":")[1].replace("}", ""));
		AdventureBooking ab = adventureBookingService.getById(adventureBookingId);

		String userName = registeredUserService.getById(userId).getName();
		String userEmail = registeredUserService.getById(userId).getEmail();
		mailService.SendMail(userEmail, userName, "Adventure booking\n\n You have successfully booked an adventure!",
				"Reservation");

		ab.setRegisteredUserId(userId);
		return adventureBookingService.save(ab) != null;
	}

	@PostMapping(path = "/quickCottageBooking")
	public Boolean quickCottageBooking(RequestEntity<String> params)
			throws AddressException, UnsupportedEncodingException {
		String[] paramsArray = params.getBody().split(",");
		Long roomBookingId = Long.parseLong(paramsArray[0].split(":")[1]);
		Long userId = Long.parseLong(paramsArray[1].split(":")[1].replace("}", ""));
		RoomBooking rb = roomBookingService.getById(roomBookingId);

		String userName = registeredUserService.getById(userId).getName();
		String userEmail = registeredUserService.getById(userId).getEmail();
		mailService.SendMail(userEmail, userName, "Cottage booking\n\n You have successfully booked a cottage!",
				"Reservation");

		rb.setRegisteredUserId(userId);
		return roomBookingService.save(rb) != null;
	}

	@PostMapping(path = "/room")
	public Boolean bookRoom(RequestEntity<String> param) throws AddressException, UnsupportedEncodingException {

		String params[] = param.getBody().split(",", 8);
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
		
		Long userId = Long.parseLong(params[6].split(":")[1].replace("\"",""));
		// String extraServices = params[7];//.split(":")[1].replace("\"", "");
		// // .replace("}", ""));


		Date startDate = date.getTime();
		date.add(Calendar.DAY_OF_MONTH, days);
		Date endDate = date.getTime();

		String extraServices = "";
		String servicesParsed = params[7];
		System.out.println(servicesParsed);
		servicesParsed = servicesParsed.replace("\"extraServices\":[", "");
		servicesParsed = servicesParsed.replace("]}", "");

		String servicesChecked[] = servicesParsed.split(",");
		Cottage cottage = cottageService.getById(cottageId);
		String priceAndInfo[] = cottage.getPriceAndInfo().split(";");
		String cottageName = cottage.getName();

		for (String fromFront : servicesChecked) {
			extraServices += priceAndInfo[Integer.parseInt(fromFront)].split(":")[0] + ";";
		}
		extraServices += "ß";
		extraServices = extraServices.replace(";ß", "");

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
				extraServices,
				guests,
				rooms.get(0).getCottage().getId());

		String userName = registeredUserService.getById(userId).getName();
		String userEmail = registeredUserService.getById(userId).getEmail();

		mailService.SendMail(userEmail, userName,
				"Room reservation. \n\n Cottage Owner has successfully made a reservation in your name in the following Cottage:"
						+ cottageName + "\n Time Period: " + startDate + " - " + endDate + ".",
				"Reservation");

		return roomBookingService.save(roomBooking) != null;
	}

	@PostMapping(path = "/boat")
	public ResponseEntity<Boolean> bookBoat(RequestEntity<String> param) throws AddressException, UnsupportedEncodingException {

		String params[] = param.getBody().split(",", 8);
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
				0);
		int days = Integer.parseInt(params[3].split(":")[1]);
		int guests = Integer.parseInt(params[4].split(":")[1]);
		// Long ownerId = Long.parseLong(params[5].split(":")[1].replace("\"", ""));
		Long userId = Long.parseLong(params[6].split(":")[1].replace("\"","").replace("}", ""));

		Date startDate = date.getTime();
		date.add(Calendar.DAY_OF_MONTH, days);
		Date endDate = date.getTime();

		String extraServices = "";
		String servicesParsed = params[7];
		servicesParsed = servicesParsed.replace("\"extraServices\":[", "");
		servicesParsed = servicesParsed.replace("]}", "");
		String servicesChecked[] = servicesParsed.split(",");
		Boat boat = boatService.getById(boatId);
		String priceAndInfo[] = boat.getPriceAndInfo().split(";");
		String boatName = boat.getName();

		for (String fromFront : servicesChecked) {
			extraServices += priceAndInfo[Integer.parseInt(fromFront)].split(":")[0] + ";";
		}
		extraServices += "ß";
		extraServices = extraServices.replace(";ß", "");

		ArrayList<Boat> boats = boatService.getAllBySearchQuery(boatName, startDate, endDate, guests);
		Boat testBoat;
		System.out.println(boats.size());
		try {
			testBoat = boats.get(0);
		} catch (Exception e) {
			System.err.println(e);
			return new ResponseEntity<>(false, HttpStatus.NOT_FOUND);
		}
		System.out.println(testBoat.getId());
		BoatBooking boatBooking = new BoatBooking(
				testBoat.getId(),
				userId,
				startDate,
				endDate,
				extraServices,
				guests);

		String userName = registeredUserService.getById(userId).getName();
		String userEmail = registeredUserService.getById(userId).getEmail();

		mailService.SendMail(userEmail, userName,
				"Boat reservation. \n\n Boat Owner has successfully made a reservation in your name in the following Boat:"
						+ boatName + "\n Time Period: " + startDate + " - " + endDate + ".",
				"Reservation");

		BoatBooking bb = boatBookingService.save(boatBooking);
		if (bb != null) {
			return new ResponseEntity<>(true, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(false, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PostMapping(path = "/adventure")
	public Boolean bookAdventure(RequestEntity<String> param) throws AddressException, UnsupportedEncodingException {

		String params[] = param.getBody().split(",", 8);
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

		Long userId = 1L;

		Date startDate = date.getTime();
		date.add(Calendar.DAY_OF_MONTH, days);
		Date endDate = date.getTime();

		String extraServices = "";
		String servicesParsed = params[7];
		servicesParsed = servicesParsed.replace("\"extraServices\":[", "");
		servicesParsed = servicesParsed.replace("]}", "");
		String servicesChecked[] = servicesParsed.split(",");
		Adventure adventure = adventureService.getById(adventureId);
		String adventureName = adventure.getName();
		String priceAndInfo[] = adventure.getPriceAndInfo().split(";");

		for (String fromFront : servicesChecked) {
			extraServices += priceAndInfo[Integer.parseInt(fromFront)].split(":")[0] + ";";
		}
		extraServices += "ß";
		extraServices = extraServices.replace(";ß", "");

		ArrayList<Adventure> adventures = adventureService.getAllBySearchQuery(adventureName, startDate, endDate,
				guests);

		AdventureBooking adventureBooking = new AdventureBooking(
				adventures.get(0).getId(),
				instructorId,
				userId,
				startDate,
				endDate,
				extraServices,
				guests);

		String userName = registeredUserService.getById(userId).getName();
		String userEmail = registeredUserService.getById(userId).getEmail();

		mailService.SendMail(userEmail, userName,
				"Adventure reservation. \n\n Fishing Instructor has successfully made a reservation in your name in the following Adventure:"
						+ adventureName + "\n Time Period: " + startDate + " - " + endDate + ".",
				"Reservation");

		AdventureBooking ab = adventureBookingService.save(adventureBooking);
		return ab != null;
	}

	@PostMapping(path = "/search")
	public ResponseEntity<ArrayList<Object>> getReservableBoats(RequestEntity<String> searchParam) {
		String query = searchParam.getBody();
		// Trim-ovanje " na pocetku i kraju
		query = query.substring(1, query.length() - 1);
		// System.out.println(query);
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
		Calendar date = Calendar.getInstance();
		date.set(Integer.parseInt(dateAndTime.split("-")[0]),
				Integer.parseInt(dateAndTime.split("-")[1]) - 1,
				Integer.parseInt(dateAndTime.split("-")[2]),
				Integer.parseInt(time.split(":")[0]),
				Integer.parseInt(time.split(":")[1]), 0);

		Date startDate = date.getTime();
		date.add(Calendar.DAY_OF_MONTH, Integer.parseInt(numberOfDays));
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
				boatBookingService.findAllByBookedBoatIdAndEndAfterAndQuickBookingAndRegisteredUserId(id),
				HttpStatus.OK);
	}

	@GetMapping(path = "/cottageBookingDeal/{id}")
	public ResponseEntity<ArrayList<RoomBooking>> cottageBookingDeal(@PathVariable("id") Long cottageId) {
		ArrayList<RoomBooking> roomBookings = roomBookingService
				.findAllByCottageIdAndEndAfterAndQuickBookingAndRegisteredUserId(cottageId);

		return new ResponseEntity<>(roomBookings, HttpStatus.OK);
	}

	@GetMapping(path = "/adventureBookingDeal/{id}")
	public ResponseEntity<ArrayList<AdventureBooking>> adventureBookingDeal(@PathVariable("id") Long id) {
		ArrayList<AdventureBooking> adventureBookings = adventureBookingService
				.findAllByBookedAdventureIdAndEndAfterAndQuickBookingAndRegisteredUserId(id);
		return new ResponseEntity<>(adventureBookings, HttpStatus.OK);
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

	@PostMapping(path = "/createAction")
	public Boolean createAction(RequestEntity<String> params) throws AddressException, UnsupportedEncodingException {
		System.out.println(params.getBody());
		String split[] = params.getBody().split(",");
		Long entityId = Long.parseLong(split[0].split(":")[1].replace("\"", ""));

		String dateStartString = split[1].split(":")[1].replace("\"", "");
		String dateEndString = split[2].split(":")[1].replace("\"", "");

		Date startDate = parseDate(dateStartString);
		Date endDate = parseDate(dateEndString);

		double price = Double.parseDouble(split[3].split(":")[1].replace("\"", ""));
		String forType = split[4].split(":")[1].replace("\"", "");

		// int validDuration = Integer.parseInt(split[5].split(":")[1].replace("\"", ""));

		String extraServices = split[6].split("\"")[3];


		HeadEntityEnum forTypeEnum;
		ArrayList<Subscription> subscriptions = new ArrayList<>();

		int maxUsers;
		switch (forType) {
			case "ADVENTURE":
				maxUsers = adventureService.getById(entityId).getMaxUsers();
				AdventureBooking ab = new AdventureBooking(
					entityId,
					startDate,
					endDate,
					price,
					maxUsers,
					adventureService.getById(entityId).getInstructorId(),
					extraServices		
				);

				forTypeEnum = HeadEntityEnum.ADVENTURE;
				subscriptions = subscriptionService.findAllByForEntityAndSubbedId(forTypeEnum, entityId);
				for (Subscription subscription : subscriptions) {
					Long userId = subscription.getSubberId();
					RegisteredUser registeredUser = registeredUserService.getById(userId);
					String userName = registeredUser.getName();
					String userEmail = registeredUser.getEmail();
					mailService.SendMail(userEmail, userName,
							"A new action has been added for an adventure you are subbscribed to!", "Action Creation");
				}

				return adventureBookingService.save(ab) != null;
			case "BOAT":
				maxUsers = boatService.getById(entityId).getMaxUsers();
				BoatBooking bb = new BoatBooking(
					entityId,
					startDate,
					endDate,
					price,
					maxUsers, 
					extraServices
				);

				forTypeEnum = HeadEntityEnum.BOAT;
				subscriptions = subscriptionService.findAllByForEntityAndSubbedId(forTypeEnum, entityId);
				for (Subscription subscription : subscriptions) {
					Long userId = subscription.getSubberId();
					RegisteredUser registeredUser = registeredUserService.getById(userId);
					String userName = registeredUser.getName();
					String userEmail = registeredUser.getEmail();
					mailService.SendMail(userEmail, userName,
							"A new action has been added for a boat you are subbscribed to!", "Action Creation");
				}

				return boatBookingService.save(bb) != null;
			case "COTTAGE":
				maxUsers = cottageService.getById(entityId).getMaxUsers();
				ArrayList<Room> rooms = roomService.findAllByCottageId(entityId);
				boolean flag = false;
				for (Room room : rooms) {
					RoomBooking rb = new RoomBooking(
						room.getId(),
						startDate,
						endDate,
						price,
						maxUsers,
						entityId, 
						extraServices
					);
					flag = roomBookingService.save(rb) != null;
				}

				forTypeEnum = HeadEntityEnum.BOAT;
				subscriptions = subscriptionService.findAllByForEntityAndSubbedId(forTypeEnum, entityId);
				for (Subscription subscription : subscriptions) {
					Long userId = subscription.getSubberId();
					RegisteredUser registeredUser = registeredUserService.getById(userId);
					String userName = registeredUser.getName();
					String userEmail = registeredUser.getEmail();
					mailService.SendMail(userEmail, userName,
							"A new action has been added for a boat you are subbscribed to!", "Action Creation");
				}

				return flag;
			default:
				System.err.println("Something went wrong >>> " + forType);
				return false;
		}
	}

	Date parseDate(String dateString) {
		Calendar date = Calendar.getInstance();
		date.set(
				Integer.parseInt(dateString.split("-")[0]),
				Integer.parseInt(dateString.split("-")[1]) - 1,
				Integer.parseInt(dateString.split("-")[2]),
				0,
				0,
				0);
		return date.getTime();
	}

}
