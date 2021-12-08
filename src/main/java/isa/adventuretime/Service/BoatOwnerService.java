package isa.adventuretime.Service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import isa.adventuretime.Entity.BoatOwner;
import isa.adventuretime.Repository.BoatOwnerRepo;

@Service
public class BoatOwnerService {
	private BoatOwnerRepo boatOwnerRepo;

	@Autowired
	public BoatOwnerService(BoatOwnerRepo repo) {
		boatOwnerRepo = repo;
	}

	public BoatOwner getById(Long Id) {
		return boatOwnerRepo.getById(Id);
	}

	public BoatOwner register(BoatOwner boatOwner) {
		return boatOwnerRepo.save(boatOwner);
	}

	public BoatOwner findByEmail(String email) {
		return boatOwnerRepo.findByEmail(email);
	}

	public BoatOwner findByEmailHash(int code) {
		return boatOwnerRepo.findByEmailHash(code);
	}

	public BoatOwner saveBoatOwner(BoatOwner boatOwner) {
		return boatOwnerRepo.save(boatOwner);
	}

	public ArrayList<BoatOwner> findAllByAuthenticated(Boolean authenticated){
		return boatOwnerRepo.findAllByAuthenticated(authenticated);
	}

	public void deleteById(Long id) {
		boatOwnerRepo.deleteById(id);
	}

}
