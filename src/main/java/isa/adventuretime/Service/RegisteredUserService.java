package isa.adventuretime.Service;

import java.util.ArrayList;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import isa.adventuretime.Entity.RegisteredUser;
import isa.adventuretime.Repository.AdventureBookingRepo;
import isa.adventuretime.Repository.BoatBookingRepo;
import isa.adventuretime.Repository.RegisteredUserRepo;
import isa.adventuretime.Repository.RoomBookingRepo;

@Service
public class RegisteredUserService {
	@Autowired
	private RegisteredUserRepo registeredUserRepo;
	@Autowired
	private AdventureBookingRepo adventureBookingRepo;
	@Autowired
	private BoatBookingRepo boatBookingRepo;
	@Autowired
	private RoomBookingRepo roomBookingRepo;

	public RegisteredUser getById(Long Id) {
		return registeredUserRepo.getById(Id);
	}

	public ArrayList<RegisteredUser> getAll() {
		return (ArrayList<RegisteredUser>) registeredUserRepo.findAll();
	}

	public RegisteredUser register(RegisteredUser user) {
		return registeredUserRepo.save(user);
	}

	public RegisteredUser findByEmail(String email) {
		return registeredUserRepo.findByEmail(email);
	}

	public RegisteredUser findByEmailHash(int code) {
		return registeredUserRepo.findByEmailHash(code);
	}

	public RegisteredUser saveRegisteredUser(RegisteredUser registeredUser) {
		return registeredUserRepo.save(registeredUser);
	}

	public Long findIdByEmailHash(int emailHash) {
		return registeredUserRepo.findIdByEmailHash(emailHash);
	}

	public Boolean markDeleted(Long id) {
		if (hasBookingInNextThreeDays(id)) {
			return false;
		} else {
			RegisteredUser registeredUser = registeredUserRepo.getById(id);
			registeredUser.setDeleted(true);
			registeredUserRepo.save(registeredUser);
			// deletionRequestRepo.findByForTypeAndRequesterId(HeadEntityEnum.REGISTERED_USER,
			// id)
			// .ifPresent(deletionRequest -> {
			// deletionRequestRepo.delete(deletionRequest);
			// });
			return true;
		}
	}

	public Boolean hasBookingInNextThreeDays(Long id) {
		Date now_plus_3 = new Date(System.currentTimeMillis() + 3 * 24 * 60 * 60 * 1000);
		return adventureBookingRepo.existsByRegisteredUserIdAndStartAfter(id, now_plus_3)
				|| boatBookingRepo.existsByRegisteredUserIdAndStartAfter(id, now_plus_3)
				|| roomBookingRepo.existsByRegisteredUserIdAndStartAfter(id, now_plus_3);
	}

	public ArrayList<RegisteredUser> getAllUsersOfBoatOwner(Long boatOwnerId){
		return registeredUserRepo.getAllUsersOfBoatOwner(boatOwnerId);
	}
}
