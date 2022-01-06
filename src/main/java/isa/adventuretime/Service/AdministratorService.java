package isa.adventuretime.Service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import isa.adventuretime.Entity.Administrator;
import isa.adventuretime.Repository.AdministratorRepo;

@Service
public class AdministratorService {
	@Autowired
	private AdministratorRepo administratorRepo;

	public Administrator getById(Long id) {
		return administratorRepo.getById(id);
	}

	public Administrator register(Administrator user) {
		return administratorRepo.save(user);
	}

	public Administrator findByEmail(String email) {
		return administratorRepo.findByEmail(email);
	}

	public Administrator saveAdministrator(Administrator administrator) {
		return administratorRepo.save(administrator);
	}

	public Administrator findByEmailHash(int emailHash) {
		return administratorRepo.findByEmailHash(emailHash);
	}

	public ArrayList<Administrator> getAll() {
		return administratorRepo.findAll();
	}

	public Boolean markDeleted(Long id) {
		Administrator administrator = administratorRepo.getById(id);
		administrator.setDeleted(true);
		administratorRepo.save(administrator);
		return true;
	}

	public ArrayList<Administrator> findAllByDeleted() {
		return administratorRepo.findAllByDeleted(false);
	}
}
