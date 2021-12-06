package isa.adventuretime.Service;

import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import isa.adventuretime.Entity.Boat;
import isa.adventuretime.Repository.BoatRepo;

@Service
public class BoatService {
	private BoatRepo boatRepo;

	@Autowired
	public BoatService(BoatRepo repo) {
		boatRepo = repo;
	}

	public ArrayList<Boat> findAll() {
		return boatRepo.findAll();
	}

	public Boat getById(Long Id) {
		return boatRepo.getById(Id);
	}
}
