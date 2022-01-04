package isa.adventuretime.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import isa.adventuretime.Entity.Adventure;
import isa.adventuretime.Entity.DeletionRequest;
import isa.adventuretime.Entity.FishingInstructor;
import isa.adventuretime.Entity.HeadEntityEnum;
import isa.adventuretime.Repository.AdventureBookingRepo;
import isa.adventuretime.Repository.AdventureRepo;
import isa.adventuretime.Repository.DeletionRequestRepo;
import isa.adventuretime.Repository.FishingInstructorRepo;

@Service
public class FishingInstructorService {
	@Autowired
	private FishingInstructorRepo fishingInstructorRepo;
	@Autowired
	private AdventureBookingRepo adventureBookingRepo;
	@Autowired
	private AdventureRepo adventureRepo;

	@Autowired
	private DeletionRequestRepo deletionRequestRepo;

	public ArrayList<FishingInstructor> getAll() {
		return (ArrayList<FishingInstructor>) fishingInstructorRepo.findAll();
	}

	public FishingInstructor getById(Long Id) {
		return fishingInstructorRepo.getById(Id);
	}

	public FishingInstructor register(FishingInstructor instructor) {
		return fishingInstructorRepo.save(instructor);
	}

	public FishingInstructor findByEmail(String email) {
		return fishingInstructorRepo.findByEmail(email);
	}

	public FishingInstructor findByEmailHash(int code) {
		return fishingInstructorRepo.findByEmailHash(code);
	}

	public FishingInstructor saveFishingInstructor(FishingInstructor fishingInstructor) {
		return fishingInstructorRepo.save(fishingInstructor);
	}

	public ArrayList<FishingInstructor> findAllByAuthenticated(Boolean authenticated) {
		return fishingInstructorRepo.findAllByAuthenticated(authenticated);
	}

	public void markDeleted(Long id) {
		Date now = new Date();
		ArrayList<Adventure> adventures = adventureRepo.findAllByInstructorId(id);
		boolean flag = false;

		for (Adventure adventure : adventures) {
			if (adventureBookingRepo.existsByBookedAdventureIdAndStartAfter(adventure.getId(), now)) {
				flag = true;
				break;
			}
		}
		if (flag) {
			for (Adventure adventure : adventures) {
				adventure.setHidden(flag);
				adventureRepo.save(adventure);
			}
		} else {
			for (Adventure adventure : adventures) {
				adventureRepo.delete(adventure);
			}
			FishingInstructor fishingInstructor = fishingInstructorRepo.getById(id);
			fishingInstructor.setDeleted(true);
			fishingInstructorRepo.save(fishingInstructor);
		}

	}

	public void createRequestForDeletion(Long id, Date date, String reason) {
		Optional<FishingInstructor> fishingInstructorOptional = fishingInstructorRepo.findById(id);
		FishingInstructor fishingInstructor = fishingInstructorOptional.get();

		DeletionRequest delReq = new DeletionRequest(id, HeadEntityEnum.FISHING_INSTRUCTOR, reason,
				fishingInstructor.getName(), fishingInstructor.getLastname());
		deletionRequestRepo.save(delReq);
	}
}
