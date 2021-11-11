package isa.adventuretime.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import isa.adventuretime.Entity.CottageOwner;
import isa.adventuretime.Entity.FishingInstructor;
import isa.adventuretime.Repository.CottageOwnerRepo;
import isa.adventuretime.Repository.FishingInstructorRepo;

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

    public CottageOwner register(CottageOwner owner){
        return cottageOwnerRepo.save(owner);
    }

    public Object findByEmail(String email) {
        return cottageOwnerRepo.findByEmail(email);
    }

    public CottageOwner findByEmailHash(int code){
        return cottageOwnerRepo.findByEmailHash(code);
    }
}
