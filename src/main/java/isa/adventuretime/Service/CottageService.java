package isa.adventuretime.Service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import isa.adventuretime.DTO.CottageWithRoomDTO;
import isa.adventuretime.Entity.Cottage;
import isa.adventuretime.Entity.Room;
import isa.adventuretime.Repository.CottageRepo;
import isa.adventuretime.Repository.RoomBookingRepo;
import isa.adventuretime.Repository.RoomRepo;

@Service
public class CottageService {
	@Autowired
	private CottageRepo cottageRepo;

	@Autowired
	private RoomRepo roomRepo;

	@Autowired
	private RoomBookingRepo roomBookingRepo;

	public ArrayList<Cottage> findAll() {
		return cottageRepo.findAll();
	}

	public Cottage getById(Long Id) {
		return cottageRepo.getById(Id);
	}

	public ArrayList<Cottage> findAllByIdIn(ArrayList<Long> ids) {
		return cottageRepo.findAllByIdIn(ids);
	}

	public Cottage save(Cottage cottage) {
		return cottageRepo.save(cottage);
	}

	public ArrayList<Cottage> findAllByOwnerId(Long id) {
		return cottageRepo.findAllByOwnerId(id);
	}

	public void deleteById(Long id) {
		cottageRepo.deleteById(id);
	}

	public ArrayList<Cottage> getAllByOwnerId(Long id) {
		return cottageRepo.getAllByOwnerId(id);
	}

	public ArrayList<CottageWithRoomDTO> getAllBySearchQuery(String searched, Date startDate, Date endDate, int guests, int grade){
		ArrayList<Cottage> potentialCottages = cottageRepo.getAllByNameContains(searched);
		ArrayList<CottageWithRoomDTO> retCottagesWithRooms = new ArrayList<>();

		for (Cottage cottage : potentialCottages) {
			if (startDate.before(cottage.getReservationStart()) || endDate.after(cottage.getReservationEnd())) {
				continue;
			}
			ArrayList<Room> rooms = roomRepo.findAllByCottageIdAndNumberOfBedsGreaterThanEqual(cottage.getId(), guests);
			for (Room room : rooms) {
				if(roomBookingRepo.findBadBookings(room.getId(), startDate, endDate).size() == 0){
					CottageWithRoomDTO cwr = new CottageWithRoomDTO(cottage, room);
					retCottagesWithRooms.add(cwr);
				}
			}	
		}
		return retCottagesWithRooms;
	}
}
