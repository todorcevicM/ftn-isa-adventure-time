package isa.adventuretime.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import isa.adventuretime.Entity.Boat;
import isa.adventuretime.Entity.BoatOwner;
import isa.adventuretime.Entity.DeletionRequest;
import isa.adventuretime.Entity.HeadEntityEnum;
import isa.adventuretime.Repository.BoatBookingRepo;
import isa.adventuretime.Repository.BoatOwnerRepo;
import isa.adventuretime.Repository.BoatRepo;
import isa.adventuretime.Repository.DeletionRequestRepo;

@Service
public class BoatOwnerService {
	@Autowired
	private BoatOwnerRepo boatOwnerRepo;

	@Autowired
	private BoatRepo boatRepo;

	@Autowired
	private BoatBookingRepo boatBookingRepo;

	@Autowired
	private DeletionRequestRepo deletionRequestRepo;

	public ArrayList<BoatOwner> getAll() {
		return (ArrayList<BoatOwner>) boatOwnerRepo.findAll();
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

	public ArrayList<BoatOwner> findAllByAuthenticatedAndDenied(Boolean authenticated, Boolean denied) {
		return boatOwnerRepo.findAllByAuthenticatedAndDenied(authenticated, denied);
	}

	public Boolean markDeleted(Long id) {
		Date now = new Date();
		ArrayList<Boat> boats = boatRepo.findAllByOwnerId(id);
		boolean flag = false;

		for (Boat boat : boats) {
			if (boatBookingRepo.existsByBookedBoatIdAndStartAfter(boat.getId(), now)) {
				flag = true;
				break;
			}
		}
		if (flag) {
			for (Boat boat : boats) {
				boat.setHidden(flag);
				boatRepo.save(boat);
			}
			return false;
		} else {
			for (Boat boat : boats) {
				boatRepo.delete(boat);
			}
			BoatOwner boatOwner = boatOwnerRepo.getById(id);
			boatOwner.setDeleted(true);
			boatOwnerRepo.save(boatOwner);

			deletionRequestRepo.findByForTypeAndRequesterId(HeadEntityEnum.BOAT_OWNER, id)
					.ifPresent(deletionRequest -> {
						deletionRequestRepo.delete(deletionRequest);
					});

			return true;
		}
	}

	public void createDeletionRequest(Long id, String reason) {
		Optional<BoatOwner> boatOwnerOptional = boatOwnerRepo.findById(id);
		BoatOwner boatOwner = boatOwnerOptional.get();

		DeletionRequest delReq = new DeletionRequest(id, HeadEntityEnum.BOAT_OWNER, reason,
				boatOwner.getName(), boatOwner.getLastname());
		deletionRequestRepo.save(delReq);
	}

	public Boolean markAuthenticated(Long id) {
		BoatOwner boatOwner = boatOwnerRepo.getById(id);
		boatOwner.setAuthenticated(true);
		boatOwnerRepo.save(boatOwner);
		return true;
	}

	public Boolean markDenied(Long id) {
		BoatOwner boatOwner = boatOwnerRepo.getById(id);
		boatOwner.setDenied(true);
		boatOwnerRepo.save(boatOwner);
		return true;
	}

}
