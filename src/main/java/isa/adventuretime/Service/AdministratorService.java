package isa.adventuretime.Service;

import java.util.Date;
import java.util.ArrayList;
import java.util.Calendar;

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

	public float reportProfitYear(){
		Calendar cal = Calendar.getInstance();
		Date today = cal.getTime();
		cal.add(Calendar.YEAR, -1);
		return administratorRepo.reportProfit(today, cal.getTime());
	}

	public float reportProfitMonth(){
		Calendar cal = Calendar.getInstance();
		Date today = cal.getTime();
		cal.add(Calendar.MONTH, -1);
		return administratorRepo.reportProfit(today, cal.getTime());
	}

	public float reportProfitWeek(){
		Calendar cal = Calendar.getInstance();
		Date today = cal.getTime();
		cal.add(Calendar.WEEK_OF_MONTH, -1);
		return administratorRepo.reportProfit(today, cal.getTime());
	}
}
