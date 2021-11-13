package isa.adventuretime.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import isa.adventuretime.Entity.Rating;
@Repository
public interface RatingRepo extends JpaRepository<Rating, Long>{
    
}
