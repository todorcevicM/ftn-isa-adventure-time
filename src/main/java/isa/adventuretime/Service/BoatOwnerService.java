package isa.adventuretime.Service;

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

	public BoatOwner getOne(Long Id) {
		return boatOwnerRepo.getOne(Id);
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
}
