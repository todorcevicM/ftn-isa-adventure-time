package isa.adventuretime.Service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import isa.adventuretime.Entity.CottageOwner;
import isa.adventuretime.Repository.CottageOwnerRepo;

@Service
public class CottageOwnerService {
	private CottageOwnerRepo cottageOwnerRepo;

	@Autowired
	public CottageOwnerService(CottageOwnerRepo aR) {
		this.cottageOwnerRepo = aR;
	}

	public CottageOwner getOne(Long id) {
		return cottageOwnerRepo.getOne(id);
	}

	public CottageOwner register(CottageOwner owner) {
		return cottageOwnerRepo.save(owner);
	}

	public CottageOwner findByEmail(String email) {
		return cottageOwnerRepo.findByEmail(email);
	}

	public CottageOwner findByEmailHash(int code) {
		return cottageOwnerRepo.findByEmailHash(code);
	}

	public CottageOwner saveCottageOwner(CottageOwner cottageOwner) {
		return cottageOwnerRepo.save(cottageOwner);
	}

	public ArrayList<CottageOwner> findAllByAuthenticated(Boolean authenticated){
		return cottageOwnerRepo.findAllByAuthenticated(authenticated);
	}
}
