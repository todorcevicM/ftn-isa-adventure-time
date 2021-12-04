package isa.adventuretime.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import isa.adventuretime.Entity.Administrator;
import isa.adventuretime.Repository.AdministratorRepo;

@Service
public class AdministratorService{
    private AdministratorRepo administratorRepo;

    @Autowired
    public AdministratorService(AdministratorRepo repo){
        this.administratorRepo = repo;
    }

    public  Administrator getById(Long id){
        return administratorRepo.getById(id);
    }
    public Administrator findByEmail(String email){
        return administratorRepo.findByEmail(email);
    }

}
