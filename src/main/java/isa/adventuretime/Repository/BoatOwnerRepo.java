package isa.adventuretime.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import isa.adventuretime.Entity.BoatOwner;

public interface BoatOwnerRepo extends JpaRepository<BoatOwner, Long> {

    BoatOwner findByEmail(String email);
    
}
