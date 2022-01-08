package isa.adventuretime.Service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import isa.adventuretime.Entity.Revision;
import isa.adventuretime.Repository.RevisionRepo;

@Service
public class RevisionsService {
    @Autowired 
    private RevisionRepo revisionsRepository;

    public ArrayList<Revision> getAll() {
        return revisionsRepository.findAll();
    }

    public Revision getById(Long id) {
        return revisionsRepository.findById(id).get();
    }

    public Revision save(Revision revision) {
        return revisionsRepository.save(revision);
    }

    public ArrayList<Revision> findAllByNotDeniedAndNotApproved() {
        return revisionsRepository.findAllByDeniedAndApproved(false, false);
    }
    
}
