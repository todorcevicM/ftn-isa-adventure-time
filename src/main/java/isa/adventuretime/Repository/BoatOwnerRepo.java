package isa.adventuretime.Repository;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import isa.adventuretime.Entity.BoatOwner;

@Repository
public interface BoatOwnerRepo extends JpaRepository<BoatOwner, Long> {

	public BoatOwner getOne(Long id);

	public BoatOwner findByEmail(String email);

	public BoatOwner findByEmailHash(int code);

	public ArrayList<BoatOwner> findAllByAuthenticatedAndDenied(boolean authenticated, boolean denied);

}
