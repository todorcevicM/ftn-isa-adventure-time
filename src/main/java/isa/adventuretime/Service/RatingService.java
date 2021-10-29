package isa.adventuretime.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import isa.adventuretime.Entity.Rating;
import isa.adventuretime.Repository.RatingRepo;

@Service
public class RatingService {
    private RatingRepo ratingRepo;

    @Autowired
    public RatingService(RatingRepo repo){
        ratingRepo = repo;
    }

    public Rating getById(Long Id){
        return ratingRepo.getById(Id);
    }
}
