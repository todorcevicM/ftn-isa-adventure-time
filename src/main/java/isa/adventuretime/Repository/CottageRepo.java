package isa.adventuretime.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import isa.adventuretime.Entity.Cottage;

public interface CottageRepo extends JpaRepository<Cottage, Long>{
    
}
