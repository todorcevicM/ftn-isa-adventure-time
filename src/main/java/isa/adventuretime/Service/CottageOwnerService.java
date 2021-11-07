package isa.adventuretime.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import isa.adventuretime.Entity.CottageOwner;
import isa.adventuretime.Repository.CottageOwnerRepo;

@Service
public class CottageOwnerService{
    private CottageOwnerRepo cottageOwnerRepo;

    @Autowired
    public CottageOwnerService(CottageOwnerRepo aR){
        this.cottageOwnerRepo = aR;
    }

    public  CottageOwner getById(Long id){
        return cottageOwnerRepo.getById(id);
    }

}