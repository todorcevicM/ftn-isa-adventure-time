package isa.adventuretime.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import isa.adventuretime.Entity.BusinessConstants;

public interface BusinessConstantsRepo extends JpaRepository<BusinessConstants, Long> {
    
}
