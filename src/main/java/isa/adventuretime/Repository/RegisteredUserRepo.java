package isa.adventuretime.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import isa.adventuretime.Entity.RegisteredUser;

public interface RegisteredUserRepo extends JpaRepository<RegisteredUser, Long>{
    
}
