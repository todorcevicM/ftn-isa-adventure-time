package isa.adventuretime.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import isa.adventuretime.Entity.BoatOwner;
@Repository
public interface BoatOwnerRepo extends JpaRepository<BoatOwner, Long> {

    BoatOwner findByEmail(String email);
    BoatOwner findByEmailHash(int code);

    
}
