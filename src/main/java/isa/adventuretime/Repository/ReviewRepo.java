package isa.adventuretime.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import isa.adventuretime.Entity.Review;
@Repository
public interface ReviewRepo extends JpaRepository<Review, Long>{
    
}
