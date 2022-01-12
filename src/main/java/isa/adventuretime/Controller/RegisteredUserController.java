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

import isa.adventuretime.DTO.AdventureNameAdventureBookingDTO;
import isa.adventuretime.DTO.BoatNameBoatBookingDTO;
import isa.adventuretime.DTO.CottageNameRoomBookingDTO;
import isa.adventuretime.DTO.PastAdventureBookingRevisionDTO;
import isa.adventuretime.DTO.PastBoatBookingRevisionDTO;
import isa.adventuretime.DTO.PastRoomBookingRevisionDTO;
import isa.adventuretime.Entity.Adventure;
import isa.adventuretime.Entity.Boat;
import isa.adventuretime.Entity.Cottage;
import isa.adventuretime.Entity.HeadEntityEnum;
import isa.adventuretime.Entity.RegisteredUser;
import isa.adventuretime.Entity.RoomBooking;
import isa.adventuretime.Entity.Subscription;
import isa.adventuretime.Service.AdventureBookingService;
import isa.adventuretime.Service.AdventureService;
import isa.adventuretime.Service.BoatBookingService;
import isa.adventuretime.Service.BoatService;
import isa.adventuretime.Service.CottageService;
import isa.adventuretime.Service.RegisteredUserService;
import isa.adventuretime.Service.RoomBookingService;
import isa.adventuretime.Service.SubscriptionService;

@RestController
@RequestMapping(path = "/api/registeredUser")
public class RegisteredUserController {
	@Autowired
	private RegisteredUserService registeredUserService;

	@Autowired
	private BoatBookingService boatBookingService;

	@Autowired
	private RoomBookingService roomBookingService;

	@Autowired
	private AdventureBookingService adventureBookingService;

	@Autowired
	private SubscriptionService subscriptionService;

	@Autowired
	private BoatService boatService;

	@Autowired
	private CottageService cottageService;

	@Autowired
	private AdventureService adventureService;

	@GetMapping(path = "/pastBoatBookings/{id}")
	public ResponseEntity<ArrayList<PastBoatBookingRevisionDTO>> getPastBoatBookings(@PathVariable("id") Long id)
			throws Exception {

		return new ResponseEntity<ArrayList<PastBoatBookingRevisionDTO>>(
				boatBookingService.findAllByRegisteredUserIdAndEndBefore(id, new java.util.Date()), HttpStatus.OK);
	}

	@GetMapping(path = "/pastAdventureBookings/{id}")
	public ResponseEntity<ArrayList<PastAdventureBookingRevisionDTO>> getPastAdventureBookings(
			@PathVariable("id") Long id) {

		return new ResponseEntity<ArrayList<PastAdventureBookingRevisionDTO>>(
				adventureBookingService.findAllByRegisteredUserIdAndEndBefore(id, new java.util.Date()), HttpStatus.OK);
	}

	@GetMapping(path = "/pastRoomBookings/{id}")
	public ResponseEntity<ArrayList<PastRoomBookingRevisionDTO>> getPastRoomBookings(@PathVariable("id") Long id)
			throws Exception {

		return new ResponseEntity<ArrayList<PastRoomBookingRevisionDTO>>(
				roomBookingService.findAllByRegisteredUserIdAndEndBefore(id, new java.util.Date()), HttpStatus.OK);
	}

	@GetMapping(path = "/futureBoatBookings/{id}")
	public ResponseEntity<ArrayList<BoatNameBoatBookingDTO>> getFutureBoatBookings(@PathVariable("id") Long id) throws Exception {
		return new ResponseEntity<ArrayList<BoatNameBoatBookingDTO>>(
				boatBookingService.findAllByRegisteredUserIdAndEndAfter(id, new java.util.Date()), HttpStatus.OK);
	}

	@GetMapping(path = "/futureAdventureBookings/{id}")
	public ResponseEntity<ArrayList<AdventureNameAdventureBookingDTO>> getFutureAdventureBookings(@PathVariable("id") Long id) {
		ArrayList<AdventureNameAdventureBookingDTO> futureAdventureBookings = adventureBookingService.findAllByRegisteredUserIdAndEndAfter(id, new java.util.Date());

		return new ResponseEntity<ArrayList<AdventureNameAdventureBookingDTO>>(futureAdventureBookings, HttpStatus.OK);
	}

	@GetMapping(path = "/futureRoomBookings/{id}")
	public ResponseEntity<ArrayList<CottageNameRoomBookingDTO>> getFutureRoomBookings(@PathVariable("id") Long id) throws Exception {
		ArrayList<CottageNameRoomBookingDTO> roomBookings = roomBookingService.findAllByRegisteredUserIdAndEndAfter(id,
				new java.util.Date());

		return new ResponseEntity<ArrayList<CottageNameRoomBookingDTO>>(
				roomBookings, HttpStatus.OK);
	}

	@GetMapping(path = "/subBoat/{id}")
	public ResponseEntity<ArrayList<Boat>> getSubBoat(@PathVariable("id") Long id) throws Exception {
		ArrayList<Subscription> subscriptions = subscriptionService.findAllSubberById(id);
		ArrayList<Long> ids = new ArrayList<>();
		for (Subscription subscription : subscriptions) {
			if (subscription.getForEntity().equals(HeadEntityEnum.BOAT))
				ids.add(subscription.getSubbedId());
		}
		return new ResponseEntity<ArrayList<Boat>>(
				boatService.findAllByIdIn(ids), HttpStatus.OK);
	}

	@GetMapping(path = "/subAdventure/{id}")
	public ResponseEntity<ArrayList<Adventure>> getSubAdventure(@PathVariable("id") Long id) {
		ArrayList<Subscription> subscriptions = subscriptionService.findAllSubberById(id);
		ArrayList<Long> ids = new ArrayList<>();
		for (Subscription subscription : subscriptions) {
			if (subscription.getForEntity().equals(HeadEntityEnum.ADVENTURE))
				ids.add(subscription.getSubbedId());
		}

		return new ResponseEntity<ArrayList<Adventure>>(
				adventureService.findAllByIdIn(ids), HttpStatus.OK);
	}

	@GetMapping(path = "/subCottage/{id}")
	public ResponseEntity<ArrayList<Cottage>> getSubCottage(@PathVariable("id") Long id) throws Exception {
		ArrayList<Subscription> subscriptions = subscriptionService.findAllSubberById(id);
		ArrayList<Long> ids = new ArrayList<>();
		for (Subscription subscription : subscriptions) {
			if (subscription.getForEntity().equals(HeadEntityEnum.COTTAGE))
				ids.add(subscription.getSubbedId());
		}

		return new ResponseEntity<ArrayList<Cottage>>(
				cottageService.findAllByIdIn(ids), HttpStatus.OK);
	}

	@GetMapping(value = ("/getByEmail/{emailHash}"))
	public ResponseEntity<RegisteredUser> getRegisteredUserByEmailHash(@PathVariable("emailHash") int emailHash) {
		RegisteredUser registeredUser = registeredUserService.findByEmailHash(emailHash);
		if (registeredUser == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<>(registeredUser, HttpStatus.OK);
		}
	}

	@GetMapping(path = "/getUser/{id}")
	public ResponseEntity<RegisteredUser> getUser(@PathVariable("id") Long id) {
		RegisteredUser registeredUser = registeredUserService.getById(id);
		if (registeredUser == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<>(registeredUser, HttpStatus.OK);
		}
	}

	@GetMapping(path = "/get")
	public ResponseEntity<ArrayList<RegisteredUser>> getAll() {
		return new ResponseEntity<>(registeredUserService.getAll(), HttpStatus.OK);
	}

	@PostMapping(path = "/delete/{id}")
	public ResponseEntity<Boolean> deleteUser(@PathVariable("id") Long id) {
		return new ResponseEntity<Boolean>(registeredUserService.markDeleted(id), HttpStatus.OK);
	}

}