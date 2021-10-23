package isa.adventuretime.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import isa.adventuretime.Entity.Subscription;

public interface SubscriptionRepo extends JpaRepository<Subscription, Long>{
    
}
