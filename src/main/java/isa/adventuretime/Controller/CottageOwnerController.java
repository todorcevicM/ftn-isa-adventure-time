package isa.adventuretime.Controller;

import java.util.ArrayList;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import isa.adventuretime.DTO.CottageNameRoomBookingDTO;
import isa.adventuretime.DTO.CottageWithRoomDTO;
import isa.adventuretime.DTO.UserNameRoomBookingDTO;
import isa.adventuretime.Entity.Cottage;
import isa.adventuretime.Entity.CottageOwner;
import isa.adventuretime.Entity.RegisteredUser;
import isa.adventuretime.Entity.Room;
import isa.adventuretime.Entity.RoomBooking;
import isa.adventuretime.Service.CottageOwnerService;
import isa.adventuretime.Service.CottageService;
import isa.adventuretime.Service.RegisteredUserService;
import isa.adventuretime.Service.RoomBookingService;
import isa.adventuretime.Service.RoomService;

@RestController
@RequestMapping("/api/cottageOwner")
public class CottageOwnerController {

	@Autowired
	private CottageOwnerService cottageOwnerService;

	@Autowired
	private RoomBookingService roomBookingService;

	@Autowired
	private CottageService cottageService;

	@Autowired
	private RegisteredUserService registeredUserService;
	@Autowired
	private RoomService roomService;


	
	@GetMapping(value = "/profitYear/{id}")
	public float reportProfitYear(@PathVariable("id") Long id){
		return cottageOwnerService.reportProfitYear(id);
	}

	@GetMapping(value = "/profitMonth/{id}")
	public float reportProfitMonth(@PathVariable("id") Long id){
		return cottageOwnerService.reportProfitMonth(id);
	}

	@GetMapping(value = "/profitWeek/{id}")
	public float reportProfitWeek(@PathVariable("id") Long id){
		return cottageOwnerService.reportProfitWeek(id);
	}


	@GetMapping("/get")
	public ResponseEntity<ArrayList<CottageOwner>> getAll() {
		return new ResponseEntity<ArrayList<CottageOwner>>(cottageOwnerService.getAll(), HttpStatus.OK);
	}

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

	@GetMapping(path = "/pastRoomBookings/{ownerId}")
	public ResponseEntity<ArrayList<CottageNameRoomBookingDTO>> getPastRoomBookings(
			@PathVariable("ownerId") Long ownerId) throws Exception {
		ArrayList<Cottage> cottages = cottageService.findAllByOwnerId(ownerId);
		ArrayList<CottageNameRoomBookingDTO> cottagesDTO = new ArrayList<>();

		Date date = new Date();

		for (Cottage cottage : cottages) {
			ArrayList<RoomBooking> roomBookings = new ArrayList<>();
			ArrayList<UserNameRoomBookingDTO> userNameRoomBookingsDTO = new ArrayList<>();
			roomBookings
					.addAll(roomBookingService.findAllByCottageIdAndEndBefore(cottage.getId(), date));

			for (RoomBooking roomBooking : roomBookings) {
				String userName = registeredUserService.getById(roomBooking.getRegisteredUserId()).getName();
				userNameRoomBookingsDTO.add(new UserNameRoomBookingDTO(userName, roomBooking));
			}

			if (roomBookings.size() > 0) {
				cottagesDTO.add(new CottageNameRoomBookingDTO(cottage.getName(), userNameRoomBookingsDTO));
			}
		}

		return new ResponseEntity<ArrayList<CottageNameRoomBookingDTO>>(cottagesDTO, HttpStatus.OK);
	}

	@GetMapping("/checkBooked/{id}")
	public ResponseEntity<Boolean> checkBooked(@PathVariable("id") Long id) {
		return new ResponseEntity<>(roomBookingService.existsByCottageId(id), HttpStatus.OK);
	}

	@PostMapping(path = "/delete/{id}")
	public ResponseEntity<Boolean> deleteCottageOwner(@PathVariable("id") Long id) {
		return new ResponseEntity<Boolean>(cottageOwnerService.markDeleted(id), HttpStatus.OK);
	}

	@GetMapping(path = "/currentCustomers/{id}")
	public ResponseEntity<ArrayList<CottageNameRoomBookingDTO>> acquireCustomer(@PathVariable("id") Long id) {
		ArrayList<RegisteredUser> registeredUsers = registeredUserService.getAllUsersOfCottageOwner(id);
		ArrayList<CottageWithRoomDTO> cottagesWithRooms = new ArrayList<>();
		Date now = new Date();

		ArrayList<Cottage> cottages = cottageService.findAllByOwnerId(id);

		for (Cottage cottage : cottages) {
			ArrayList<Room> rooms = roomService.findAllByCottageId(cottage.getId());
			for (Room room : rooms) {
				cottagesWithRooms.add(new CottageWithRoomDTO(cottage, room));
			}
		}
		ArrayList<UserNameRoomBookingDTO> userNameRoomBookingDTO = new ArrayList<>();
		ArrayList<CottageWithRoomDTO> cwr = new ArrayList<>();
		for (CottageWithRoomDTO cottageWithRoom : cottagesWithRooms) {
			ArrayList<RoomBooking> roomBookings = new ArrayList<>();
			for (RegisteredUser registeredUser : registeredUsers) {

				String userNameLastname = registeredUser.getName() + " " + registeredUser.getLastname();
				roomBookings = roomBookingService.findAllByBookedRoomIdAndRegisteredUserIdAndStartBeforeAndEndAfter(cottageWithRoom.getRoom().getId(), registeredUser.getId(), now, now);
				for (RoomBooking roomBooking : roomBookings) {
					userNameRoomBookingDTO.add(new UserNameRoomBookingDTO(userNameLastname, roomBooking));
					cwr.add(new CottageWithRoomDTO(cottageService.getById(roomBooking.getCottageId()), roomService.getById(roomBooking.getId())));
				}
			}
		}
		ArrayList<Cottage> co = new ArrayList<>();
		ArrayList<Integer> indexes = new ArrayList<>();
		for (CottageWithRoomDTO cwr1 : cwr) {
			co.add(cwr1.getCottage());
		}
		int a = 0;
		for(Cottage c : co) {
			if (co.indexOf(c) != -1) {
				indexes.add(co.indexOf(c));
			}
			else {
				indexes.add(a);
			}
			a++;
		}
		ArrayList<Integer> indexes_copy = new ArrayList<>(indexes);
		int insize = indexes.size();
		for (int i = 0; i < insize; i++) {
			int j = indexes.get(i);
			if (j < i) {
				indexes.remove(i);
			}
		}
		ArrayList<Cottage> cottagesToBeUsed = new ArrayList<>();
		for (int i : indexes) {
			cottagesToBeUsed.add(co.get(i));
		}
		ArrayList<CottageNameRoomBookingDTO> conm = new ArrayList<>(); 
		for (int i : indexes) {
			a = 0;
			ArrayList<UserNameRoomBookingDTO> toAdd = new ArrayList<>();
			for (int j : indexes_copy) {
				if (i == j) {
					toAdd.add(userNameRoomBookingDTO.get(a));
				}
				a++;
			}
			conm.add(new CottageNameRoomBookingDTO(co.get(i).getName(), toAdd));
		}
		
		// for (CottageNameRoomBookingDTO c : conm) {
		// 	System.out.println("Cottage Name: " + c.getCottageName());
		// 	for (UserNameRoomBookingDTO u : c.getUserNameRoomBookingDTO()) {
		// 		System.out.println("User: " + u.getUserName());
		// 		System.out.println("Room Booking Id: " + u.getRoomBooking().getId());				
		// 	}
		// }
	
		return new ResponseEntity<ArrayList<CottageNameRoomBookingDTO>>(conm, HttpStatus.OK);
	}
}