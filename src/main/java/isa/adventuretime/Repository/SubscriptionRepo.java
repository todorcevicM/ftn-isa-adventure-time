package isa.adventuretime.Repository;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import isa.adventuretime.Entity.Subscription;

@Repository
public interface SubscriptionRepo extends JpaRepository<Subscription, Long> {
	public ArrayList<Subscription> findAllBySubberId(Long id);

	public Subscription save(Subscription subscription);
}
