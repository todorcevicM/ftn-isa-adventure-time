package isa.adventuretime.Repository;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import isa.adventuretime.Entity.Appeal;

@Repository
public interface AppealRepo extends JpaRepository<Appeal, Long> {

	public ArrayList<Appeal> findAll(); 
}
