package isa.adventuretime.Repository;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;

import isa.adventuretime.Entity.Adventure;

public interface AdventureRepo extends JpaRepository<Adventure, Long> {
	public ArrayList<Adventure> findAll();
}
