package isa.adventuretime.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import isa.adventuretime.Entity.Subscription;
import isa.adventuretime.Repository.SubscriptionRepo;

@Service
public class SubscriptionService{
    private SubscriptionRepo subscriptionRepo;

    @Autowired
    public SubscriptionService(SubscriptionRepo aR){
        this.subscriptionRepo = aR;
    }

    public  Subscription getById(Long id){
        return subscriptionRepo.getById(id);
    }

}
