package isa.adventuretime.Service;

import java.util.ArrayList;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import isa.adventuretime.Entity.Boat;
import isa.adventuretime.Repository.BoatBookingRepo;
import isa.adventuretime.Repository.BoatRepo;

@Service
public class BoatService {

	@Autowired
	private BoatRepo boatRepo;

	@Autowired
	private BoatBookingRepo boatBookingRepo;

	public ArrayList<Boat> findAll() {
		return boatRepo.findAll();
	}

	public Boat getById(Long Id) {
		return boatRepo.getById(Id);
	}

	public ArrayList<Boat> findAllByIdIn(ArrayList<Long> ids) {
		return boatRepo.findAllByIdIn(ids);
	}

	public void deleteById(Long id) {
		boatRepo.deleteById(id);
	}

	public ArrayList<Boat> getPastBoatsByOwnerId(Long id) {
		return boatRepo.getPastBoatsByOwnerId(id);
	}

	public Boat save(Boat boat) {
		return boatRepo.save(boat);
	}

	// TODO: test this
	public ArrayList<Boat> getAllBySearchQuery(String searched, Date startDate, Date endDate, int guests) {
		ArrayList<Boat> potentialBoats = boatRepo.getAllByNameContainsAndMaxUsersGreaterThanEqual(searched, guests);
		ArrayList<Boat> retBoats = new ArrayList<>();

		for (Boat boat : potentialBoats) {
			if (startDate.before(boat.getReservationStart()) || endDate.after(boat.getReservationEnd()))
				continue;
			if (boatBookingRepo.findBadBookings(boat.getId(), startDate, endDate).size() == 0) {
				retBoats.add(boat);
			}
		}
		return retBoats;
	}

	public ArrayList<Boat> findByOwnerId(Long id) {
		return boatRepo.findByOwnerId(id);
	}
}
