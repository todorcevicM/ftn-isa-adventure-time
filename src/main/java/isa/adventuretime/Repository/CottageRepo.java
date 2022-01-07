package isa.adventuretime.Repository;

import java.util.ArrayList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import isa.adventuretime.Entity.Cottage;

@Repository
public interface CottageRepo extends JpaRepository<Cottage, Long> {
	public ArrayList<Cottage> findAll();

	public ArrayList<Cottage> findAllByOwnerId(Long id);

	public ArrayList<Cottage> findAllByIdIn(ArrayList<Long> ids);

	public ArrayList<Cottage> getAllByOwnerId(Long id);

	public ArrayList<Cottage> getAllByNameContainsAndMaxUsersGreaterThanEqual(String name, int maxUsers);

	public ArrayList<Cottage> getAllByNameContains(String name);

}
