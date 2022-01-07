package isa.adventuretime.Service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import isa.adventuretime.Entity.Appeal;
import isa.adventuretime.Repository.AppealRepo;

@Service
public class AppealService {
    @Autowired 
    private AppealRepo revisionsRepository;

    public ArrayList<Appeal> getAll() {
        return revisionsRepository.findAll();
    }

    public Appeal getById(Long id) {
        return revisionsRepository.findById(id).get();
    }

    public Appeal save(Appeal appeal) {
        return revisionsRepository.save(appeal);
    }
    
}
