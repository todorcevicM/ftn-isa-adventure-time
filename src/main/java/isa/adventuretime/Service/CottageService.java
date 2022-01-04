package isa.adventuretime.Service;

import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import isa.adventuretime.Entity.Cottage;
import isa.adventuretime.Repository.CottageRepo;

@Service
public class CottageService {
	@Autowired
	private CottageRepo cottageRepo;

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
}
