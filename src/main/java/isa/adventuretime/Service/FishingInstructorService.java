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
}