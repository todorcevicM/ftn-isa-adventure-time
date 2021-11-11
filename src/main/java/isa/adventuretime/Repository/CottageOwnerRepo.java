package isa.adventuretime.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import isa.adventuretime.Entity.CottageOwner;

public interface CottageOwnerRepo extends JpaRepository<CottageOwner, Long>{

    CottageOwner findByEmail(String email);
    CottageOwner findByEmailHash(int code);

}
