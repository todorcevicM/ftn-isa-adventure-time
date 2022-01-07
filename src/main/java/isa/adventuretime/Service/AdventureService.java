package isa.adventuretime.Service;

import java.util.ArrayList;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import isa.adventuretime.Entity.Adventure;
import isa.adventuretime.Repository.AdventureBookingRepo;
import isa.adventuretime.Repository.AdventureRepo;

@Service
public class AdventureService {
	@Autowired
	private AdventureRepo adventureRepo;
	@Autowired
	private AdventureBookingRepo adventureBookingRepo;

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

	public ArrayList<Adventure> getAllBySearchQuery(String searched, Date startDate, Date endDate, int guests, int grade){

		ArrayList<Adventure> adventures = adventureRepo.findAllByNameContainsAndMaxUsers(searched, guests);
		ArrayList<Adventure> retAdventures = new ArrayList<>();
		for (Adventure adventure : adventures) {
			if(!adventureBookingRepo.existsByBookedInstructorIdAndStartBetweenOrBookedInstructorIdAndEndBetween(adventure.getInstructorId(), startDate, endDate, adventure.getInstructorId(), startDate, endDate)){
				retAdventures.add(adventure);
			}
		}
		return retAdventures;
	}
}
