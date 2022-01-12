package isa.adventuretime.Service;

import java.util.ArrayList;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import isa.adventuretime.Entity.Adventure;
import isa.adventuretime.Entity.FishingInstructor;
import isa.adventuretime.Repository.AdventureBookingRepo;
import isa.adventuretime.Repository.AdventureRepo;
import isa.adventuretime.Repository.FishingInstructorRepo;

@Service
public class AdventureService {
	@Autowired
	private AdventureRepo adventureRepo;
	@Autowired
	private AdventureBookingRepo adventureBookingRepo;
	@Autowired
	private FishingInstructorRepo fishingInstructorRepo;

	public Adventure getById(Long Id) {
		return adventureRepo.getById(Id);
	}

	public ArrayList<Adventure> findAll() {
		return adventureRepo.findAll();
	}

	public ArrayList<Adventure> findAllByIdIn(ArrayList<Long> ids) {
		return adventureRepo.findAllByIdIn(ids);
	}

	public Adventure save(Adventure adventure) {
		return adventureRepo.save(adventure);
	}

	public ArrayList<Adventure> getPastAdventuresByInstructorId(Long id) {
		return adventureRepo.getPastAdventuresByInstructorId(id);
	}

	public void deleteById(Long id) {
		adventureRepo.deleteById(id);
	}

	public ArrayList<Adventure> getAllBySearchQuery(String searched, Date startDate, Date endDate, int guests) {
		ArrayList<Adventure> adventures = adventureRepo.getAllByNameContainsAndMaxUsersGreaterThanEqual(searched,
				guests);
		ArrayList<Adventure> retAdventures = new ArrayList<>();

		for (Adventure adventure : adventures) {
			FishingInstructor fi = fishingInstructorRepo.getById(adventure.getInstructorId());
			System.out.println(fi.getName());
			if (startDate.before(fi.getStartWorkPeriod()) || endDate.after(fi.getEndWorkPeriod())) {
				continue;
			}
			if (adventureBookingRepo.findBadBookings(adventure.getInstructorId(), startDate, endDate).size() == 0) {
				retAdventures.add(adventure);
			}
		}
		System.out.println(retAdventures.size());
		return retAdventures;
	}

	public ArrayList<Adventure> findByInstructorId(Long id) {
		return adventureRepo.findByInstructorId(id);
	}
}
