package isa.adventuretime.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import isa.adventuretime.Entity.FishingInstructor;

public interface FishingInstructorRepo extends JpaRepository<FishingInstructor, Long>{

    FishingInstructor findByEmail(String email);
    
}
