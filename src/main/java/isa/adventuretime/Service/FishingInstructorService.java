package isa.adventuretime.Service;

import java.util.ArrayList;
import java.util.Calendar;
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

	public ArrayList<FishingInstructor> findAllByAuthenticatedAndDenied(Boolean authenticated, Boolean denied) {
		return fishingInstructorRepo.findAllByAuthenticatedAndDenied(authenticated, denied);
	}

	public Boolean markDeleted(Long id) {
		Date now = new Date();
		ArrayList<Adventure> adventures = adventureRepo.findAllByInstructorId(id);
		Boolean flag = false;

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
			return false;
		} else {
			for (Adventure adventure : adventures) {
				adventureRepo.delete(adventure);
			}
			FishingInstructor fishingInstructor = fishingInstructorRepo.getById(id);
			fishingInstructor.setDeleted(true);
			fishingInstructorRepo.save(fishingInstructor);
			deletionRequestRepo.findByForTypeAndRequesterId(HeadEntityEnum.FISHING_INSTRUCTOR, id)
					.ifPresent(deletionRequest -> {
						deletionRequestRepo.delete(deletionRequest);
					});
			return true;
		}

	}

	public void createDeletionRequest(Long id, String reason) {
		Optional<FishingInstructor> fishingInstructorOptional = fishingInstructorRepo.findById(id);
		FishingInstructor fishingInstructor = fishingInstructorOptional.get();

		DeletionRequest delReq = new DeletionRequest(id, HeadEntityEnum.FISHING_INSTRUCTOR, reason,
				fishingInstructor.getName(), fishingInstructor.getLastname());
		deletionRequestRepo.save(delReq);
	}

	public Boolean markAuthenticated(Long id) {
		FishingInstructor fishingInstructor = fishingInstructorRepo.getById(id);
		fishingInstructor.setAuthenticated(true);
		fishingInstructorRepo.save(fishingInstructor);
		return true;
	}
	
	public Boolean markDenied(Long id) {
		FishingInstructor fishingInstructor = fishingInstructorRepo.getById(id);
		fishingInstructor.setDenied(true);
		fishingInstructorRepo.save(fishingInstructor);
		return true;
	}


	public float reportProfitYear(Long id){
		Calendar cal = Calendar.getInstance();
		Date today = cal.getTime();
		cal.add(Calendar.YEAR, -1);
		return fishingInstructorRepo.reportProfit(today, cal.getTime(), id);
	}

	public float reportProfitMonth(Long id){
		Calendar cal = Calendar.getInstance();
		Date today = cal.getTime();
		cal.add(Calendar.MONTH, -1);
		return fishingInstructorRepo.reportProfit(today, cal.getTime(), id);
	}

	public float reportProfitWeek(Long id){
		Calendar cal = Calendar.getInstance();
		Date today = cal.getTime();
		cal.add(Calendar.WEEK_OF_MONTH, -1);
		return fishingInstructorRepo.reportProfit(today, cal.getTime(), id);
	}

}
