package isa.adventuretime.Service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import isa.adventuretime.Entity.HeadEntityEnum;
import isa.adventuretime.Entity.Subscription;
import isa.adventuretime.Repository.SubscriptionRepo;

@Service
public class SubscriptionService {
	@Autowired
	private SubscriptionRepo subscriptionRepo;

	public Subscription getById(Long id) {
		return subscriptionRepo.getById(id);
	}

	public ArrayList<Subscription> findAllSubberById(Long id) {
		return subscriptionRepo.findAllBySubberId(id);
	}

	public Subscription save(Subscription subscription) {
		return subscriptionRepo.save(subscription);
	}

	public void delete(Subscription subscription){
		subscriptionRepo.delete(subscription);
	}

	public Boolean existsBySubberIdAndSubbedIdAndForEntity(Long id1, Long id2, HeadEntityEnum id3){
		return subscriptionRepo.existsBySubberIdAndSubbedIdAndForEntity(id1, id2, id3);
	}

	public Subscription getBySubberIdAndSubbedIdAndForEntity(Long id1, Long id2, HeadEntityEnum id3){
		return subscriptionRepo.getBySubberIdAndSubbedIdAndForEntity(id1, id2, id3);
	}


	public ArrayList<Subscription> findAllByForEntityAndSubbedId(HeadEntityEnum forType, Long id) {
		return subscriptionRepo.findAllByForEntityAndSubbedId(forType, id);
	}

}
