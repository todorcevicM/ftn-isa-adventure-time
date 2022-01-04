package isa.adventuretime.Service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import isa.adventuretime.Entity.DeletionRequest;
import isa.adventuretime.Entity.HeadEntityEnum;
import isa.adventuretime.Repository.DeletionRequestRepo;

@Service
public class DeletionRequestService {
	@Autowired
	private DeletionRequestRepo deletionRequestRepo;

	public DeletionRequest getById(Long Id) {
		return deletionRequestRepo.getById(Id);
	}

	public ArrayList<DeletionRequest> getAll() {
		return (ArrayList<DeletionRequest>) deletionRequestRepo.findAll();
	}

	public DeletionRequest save(DeletionRequest dr) {
		return deletionRequestRepo.save(dr);
	}

	public void deleteById(Long id) {
		deletionRequestRepo.deleteById(id);
	}

	public void delete(DeletionRequest deletionRequest) {
		deletionRequestRepo.delete(deletionRequest);
	}

	public DeletionRequest getByForTypeAndRequesterId(HeadEntityEnum forType, Long requesterId) {
		return deletionRequestRepo.getByForTypeAndRequesterId(forType, requesterId);
	}
}