package isa.adventuretime.Repository;

import java.util.ArrayList;
import org.springframework.data.jpa.repository.JpaRepository;
import isa.adventuretime.Entity.Cottage;

public interface CottageRepo extends JpaRepository<Cottage, Long> {
	public ArrayList<Cottage> findAll();
}
