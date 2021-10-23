package isa.adventuretime.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import isa.adventuretime.Entity.Review;

public interface ReviewRepo extends JpaRepository<Review, Long>{
    
}
