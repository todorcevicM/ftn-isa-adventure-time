package isa.adventuretime.Service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import isa.adventuretime.Entity.Cottage;
import isa.adventuretime.Entity.CottageOwner;
import isa.adventuretime.Entity.DeletionRequest;
import isa.adventuretime.Entity.HeadEntityEnum;
import isa.adventuretime.Entity.Room;
import isa.adventuretime.Repository.CottageOwnerRepo;
import isa.adventuretime.Repository.CottageRepo;
import isa.adventuretime.Repository.DeletionRequestRepo;
import isa.adventuretime.Repository.RoomBookingRepo;
import isa.adventuretime.Repository.RoomRepo;

@Service
public class CottageOwnerService {
	@Autowired
	private CottageOwnerRepo cottageOwnerRepo;
	@Autowired
	private RoomBookingRepo roomBookingRepo;
	@Autowired
	private CottageRepo cottageRepo;
	@Autowired
	private RoomRepo roomRepo;

	@Autowired
	private DeletionRequestRepo deletionRequestRepo;

	public ArrayList<CottageOwner> getAll() {
		return (ArrayList<CottageOwner>) cottageOwnerRepo.findAll();
	}

	public CottageOwner getById(Long id) {
		return cottageOwnerRepo.getById(id);
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

	public ArrayList<CottageOwner> findAllByAuthenticatedAndDenied(Boolean authenticated, Boolean denied) {
		return cottageOwnerRepo.findAllByAuthenticatedAndDenied(authenticated, denied);
	}

	public Boolean markDeleted(Long id) {
		Date now = new Date();
		ArrayList<Cottage> cottages = cottageRepo.findAllByOwnerId(id);
		Boolean flag = false;

		for (Cottage cottage : cottages) {
			ArrayList<Room> rooms = roomRepo.findAllByCottageId(cottage.getId());
			for (Room room : rooms) {
				if (roomBookingRepo.existsByBookedRoomIdAndStartAfter(room.getId(), now)) {
					flag = true;
					break;
				}
			}
		}
		if (flag) {
			for (Cottage cottage : cottages) {
				ArrayList<Room> rooms = roomRepo.findAllByCottageId(cottage.getId());
				for (Room room : rooms) {
					room.setHidden(flag);
					roomRepo.save(room);
				}
				cottage.setHidden(flag);
				cottageRepo.save(cottage);
			}
			return false;
		} else {
			for (Cottage cottage : cottages) {
				ArrayList<Room> rooms = roomRepo.findAllByCottageId(cottage.getId());
				for (Room room : rooms) {
					roomRepo.deleteById(room.getId());
				}
				cottageRepo.delete(cottage);
			}
			CottageOwner cottageOwner = cottageOwnerRepo.getById(id);
			cottageOwner.setDeleted(true);
			cottageOwnerRepo.save(cottageOwner);

			deletionRequestRepo.findByForTypeAndRequesterId(HeadEntityEnum.COTTAGE_OWNER, id)
					.ifPresent(deletionRequest -> {
						deletionRequestRepo.delete(deletionRequest);
					});

			return true;
		}
	}

	public void createDeletionRequest(Long id, String reason) {
		Optional<CottageOwner> cottageOwnerOptional = cottageOwnerRepo.findById(id);
		CottageOwner cottageOwner = cottageOwnerOptional.get();

		DeletionRequest delReq = new DeletionRequest(id, HeadEntityEnum.COTTAGE_OWNER, reason,
				cottageOwner.getName(), cottageOwner.getLastname());
		deletionRequestRepo.save(delReq);
	}

	public Boolean markAuthenticated(Long id) {
		CottageOwner cottageOwner = cottageOwnerRepo.getById(id);
		cottageOwner.setAuthenticated(true);
		cottageOwnerRepo.save(cottageOwner);
		return true;
	}

	public Boolean markDenied(Long id) {
		CottageOwner cottageOwner = cottageOwnerRepo.getById(id);
		cottageOwner.setAuthenticated(false);
		cottageOwnerRepo.save(cottageOwner);
		return true;
	}


	public float reportProfitYear(Long id){
		Calendar cal = Calendar.getInstance();
		Date today = cal.getTime();
		cal.add(Calendar.YEAR, -1);
		return cottageOwnerRepo.reportProfit(today, cal.getTime(), id);
	}

	public float reportProfitMonth(Long id){
		Calendar cal = Calendar.getInstance();
		Date today = cal.getTime();
		cal.add(Calendar.MONTH, -1);
		return cottageOwnerRepo.reportProfit(today, cal.getTime(), id);
	}

	public float reportProfitWeek(Long id){
		Calendar cal = Calendar.getInstance();
		Date today = cal.getTime();
		cal.add(Calendar.WEEK_OF_MONTH, -1);
		return cottageOwnerRepo.reportProfit(today, cal.getTime(), id);
	}
}
