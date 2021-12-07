package isa.adventuretime.Repository;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import isa.adventuretime.Entity.CottageOwner;

@Repository
public interface CottageOwnerRepo extends JpaRepository<CottageOwner, Long> {

	public CottageOwner getOne(Long id);

	public CottageOwner findByEmail(String email);

	public CottageOwner findByEmailHash(int code);

	public ArrayList<CottageOwner> findAllByAuthenticated(boolean authenticated);

}
