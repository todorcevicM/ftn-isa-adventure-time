package isa.adventuretime.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import isa.adventuretime.Entity.Subscription;
@Repository
public interface SubscriptionRepo extends JpaRepository<Subscription, Long>{
    
}
