package isa.adventuretime.Service;

import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import isa.adventuretime.Entity.Cottage;
import isa.adventuretime.Repository.CottageRepo;

@Service
public class CottageService {
	private CottageRepo cottageRepo;

	@Autowired
	public CottageService(CottageRepo aR) {
		cottageRepo = aR;
	}

	public ArrayList<Cottage> findAll() {
		return cottageRepo.findAll();
	}

	public Cottage getById(Long Id) {
		return cottageRepo.getById(Id);
	}

	public ArrayList<Cottage> findAllByIdIn(ArrayList<Long> ids) {
		return cottageRepo.findAllByIdIn(ids);
	}
}
