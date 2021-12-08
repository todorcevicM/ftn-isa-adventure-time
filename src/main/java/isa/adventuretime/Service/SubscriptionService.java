package isa.adventuretime.Service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import isa.adventuretime.Entity.Subscription;
import isa.adventuretime.Repository.SubscriptionRepo;

@Service
public class SubscriptionService {
	private SubscriptionRepo subscriptionRepo;

	@Autowired
	public SubscriptionService(SubscriptionRepo aR) {
		this.subscriptionRepo = aR;
	}

	public Subscription getById(Long id) {
		return subscriptionRepo.getById(id);
	}

	public ArrayList<Subscription> findAllSubberById(Long id) {
		return subscriptionRepo.findAllBySubberId(id);
	}

	public Subscription save(Subscription subscription) {
		return subscriptionRepo.save(subscription);
	}
}
