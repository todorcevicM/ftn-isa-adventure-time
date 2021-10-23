package isa.adventuretime.Repository;


import org.springframework.data.jpa.repository.JpaRepository;

import isa.adventuretime.Entity.Boat;
    
public interface BoatRepo extends JpaRepository<Boat, Long>{
        
}