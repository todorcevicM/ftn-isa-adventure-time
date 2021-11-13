package isa.adventuretime.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import isa.adventuretime.Entity.CottageOwner;
@Repository
public interface CottageOwnerRepo extends JpaRepository<CottageOwner, Long>{

    CottageOwner findByEmail(String email);
    CottageOwner findByEmailHash(int code);

}
