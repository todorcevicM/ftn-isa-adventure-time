package isa.adventuretime.Service;

import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import isa.adventuretime.Entity.RegisteredUser;
import isa.adventuretime.Repository.RegisteredUserRepo;
import isa.adventuretime.Repository.AdventureBookingRepo;
import isa.adventuretime.Repository.BoatBookingRepo;
import isa.adventuretime.Repository.RoomBookingRepo;
import java.util.Date;

@Service
public class RegisteredUserService {
	private RegisteredUserRepo registeredUserRepo;
	private AdventureBookingRepo adventureBookingRepo;
	private BoatBookingRepo boatBookingRepo;
	private RoomBookingRepo roomBookingRepo;

	@Autowired
	public RegisteredUserService(RegisteredUserRepo registeredUserRepo, AdventureBookingRepo adventureBookingRepo,
			BoatBookingRepo boatBookingRepo, RoomBookingRepo roomBookingRepo) {
		this.registeredUserRepo = registeredUserRepo;
		this.adventureBookingRepo = adventureBookingRepo;
		this.boatBookingRepo = boatBookingRepo;
		this.roomBookingRepo = roomBookingRepo;
	}

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
			return true;
		}
	}

	public Boolean hasBookingInNextThreeDays(Long id) {
		// da li se id nalazi u nekom od bookinga
		Date now_plus_3 = new Date(System.currentTimeMillis() + 3 * 24 * 60 * 60 * 1000);
		return adventureBookingRepo.existsByRegisteredUserIdAndStartAfter(id, now_plus_3)
				|| boatBookingRepo.existsByRegisteredUserIdAndStartAfter(id, now_plus_3)
				|| roomBookingRepo.existsByRegisteredUserIdAndStartAfter(id, now_plus_3);
	}
}
