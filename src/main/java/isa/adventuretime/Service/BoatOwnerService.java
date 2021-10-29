package isa.adventuretime.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import isa.adventuretime.Entity.BoatOwner;
import isa.adventuretime.Repository.BoatOwnerRepo;

@Service
public class BoatOwnerService{
    private BoatOwnerRepo boatOwnerRepo;

    @Autowired
    public BoatOwnerService(BoatOwnerRepo repo){
        boatOwnerRepo = repo;
    }

    public BoatOwner getById(Long Id){
        return boatOwnerRepo.getById(Id);
    }
}
