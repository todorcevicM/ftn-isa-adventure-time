package isa.adventuretime.Service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import isa.adventuretime.Entity.RequestForAdmin;
import isa.adventuretime.Repository.RequestForAdminRepo;

@Service
public class RequestForAdminService {
	private RequestForAdminRepo requestForAdminRepo;

	@Autowired
	public RequestForAdminService(RequestForAdminRepo repo) {
		requestForAdminRepo = repo;
	}

	public RequestForAdmin getById(Long Id) {
		return requestForAdminRepo.getById(Id);
	}

	public ArrayList<RequestForAdmin> findAll() {
		return requestForAdminRepo.findAll();
	}

	public RequestForAdmin saveRequestForAdmin(RequestForAdmin requestForAdmin) {
		return requestForAdminRepo.save(requestForAdmin);
	}

}
