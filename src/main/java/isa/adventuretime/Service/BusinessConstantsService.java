package isa.adventuretime.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import isa.adventuretime.Entity.BusinessConstants;
import isa.adventuretime.Repository.BusinessConstantsRepo;

@Service
public class BusinessConstantsService {
	private BusinessConstantsRepo businessConstantsRepo;

	@Autowired
	public BusinessConstantsService(BusinessConstantsRepo businessConstantsRepo) {
		this.businessConstantsRepo = businessConstantsRepo;
	}

	public BusinessConstants getById(Long id) {
		return businessConstantsRepo.getById(id);
	}

	public BusinessConstants save(BusinessConstants businessConstants) {
		return businessConstantsRepo.save(businessConstants);
	}

	public BusinessConstants getBusinessConstantsByName(String name) {
		return businessConstantsRepo.getByName(name);
	}
}
