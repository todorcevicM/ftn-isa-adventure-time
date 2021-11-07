package isa.adventuretime.Service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import isa.adventuretime.Entity.Adventure;
import isa.adventuretime.Repository.AdventureRepo;

@Service
public class AdventureService {
	private AdventureRepo adventureRepo;

	@Autowired
	public AdventureService(AdventureRepo repo) {
		adventureRepo = repo;
	}

	public Adventure getById(Long Id) {
		return adventureRepo.getById(Id);
	}

	public ArrayList<Adventure> findAll() {
		return adventureRepo.findAll();
	}
}
