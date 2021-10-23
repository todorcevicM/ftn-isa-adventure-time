package isa.adventuretime.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import isa.adventuretime.Entity.RequestForAdmin;

public interface RequestForAdminRepo extends JpaRepository<RequestForAdmin, Long>{
    
}
