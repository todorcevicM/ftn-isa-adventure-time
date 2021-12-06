package isa.adventuretime.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import isa.adventuretime.Entity.CottageOwner;

@Repository
public interface CottageOwnerRepo extends JpaRepository<CottageOwner, Long> {

	public CottageOwner findByEmail(String email);

	public CottageOwner findByEmailHash(int code);
}
