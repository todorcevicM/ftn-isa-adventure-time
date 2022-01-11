package isa.adventuretime.Repository;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import isa.adventuretime.Entity.HeadEntityEnum;
import isa.adventuretime.Entity.Subscription;

@Repository
public interface SubscriptionRepo extends JpaRepository<Subscription, Long> {
	public ArrayList<Subscription> findAllBySubberId(Long id);

	public Boolean existsBySubberIdAndSubbedIdAndForEntity(Long id1, Long id2, HeadEntityEnum id3);

	public Subscription getBySubberIdAndSubbedIdAndForEntity(Long id1, Long id2, HeadEntityEnum id3);

	public ArrayList<Subscription> findAllByForEntityAndSubbedId(HeadEntityEnum forEntity, Long id);	
}
