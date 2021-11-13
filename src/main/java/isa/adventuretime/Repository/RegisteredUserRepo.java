package isa.adventuretime.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import isa.adventuretime.Entity.RegisteredUser;
@Repository
public interface RegisteredUserRepo extends JpaRepository<RegisteredUser, Long>{

    RegisteredUser findByEmail(String email);
    RegisteredUser findByEmailHash(int code);
}
