package isa.adventuretime.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import isa.adventuretime.Entity.FishingInstructor;
import isa.adventuretime.Repository.FishingInstructorRepo;

@Service
public class FishingInstructorService{
    private FishingInstructorRepo fishingInstructorRepo;

    @Autowired
    public FishingInstructorService(FishingInstructorRepo repo){
        fishingInstructorRepo = repo;
    }

    public FishingInstructor getById(Long Id){
        return fishingInstructorRepo.getById(Id);
    }

    public FishingInstructor register(FishingInstructor instructor){
        return fishingInstructorRepo.save(instructor);
    }

    public FishingInstructor findByEmail(String email) {
        return fishingInstructorRepo.findByEmail(email);
    }

    public FishingInstructor findByEmailHash(int code){
        return fishingInstructorRepo.findByEmailHash(code);
    }

    public FishingInstructor saveFishingInstructor(FishingInstructor fishingInstructor){
        return fishingInstructorRepo.save(fishingInstructor);
    }
}
