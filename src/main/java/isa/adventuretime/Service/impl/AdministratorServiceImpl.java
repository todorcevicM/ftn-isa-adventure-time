package isa.adventuretime.Service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import isa.adventuretime.Entity.Administrator;
import isa.adventuretime.Repository.AdministratorRepo;
import isa.adventuretime.Service.AdministratorService;

@Service
public class AdministratorServiceImpl implements AdministratorService {
    private AdministratorRepo administratorRepo;

    @Autowired
    public AdministratorServiceImpl(AdministratorRepo aR){
        this.administratorRepo = aR;
    }

    @Override
    public  Administrator getById(Long id){
        return administratorRepo.getById(id);
    }

}
