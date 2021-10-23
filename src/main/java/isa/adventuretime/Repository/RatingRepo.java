package isa.adventuretime.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import isa.adventuretime.Entity.Rating;

public interface RatingRepo extends JpaRepository<Rating, Long>{
    
}
