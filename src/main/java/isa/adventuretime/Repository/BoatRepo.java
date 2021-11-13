package isa.adventuretime.Repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import isa.adventuretime.Entity.Boat;
@Repository
public interface BoatRepo extends JpaRepository<Boat, Long>{
        
}