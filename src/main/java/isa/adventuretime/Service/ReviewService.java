package isa.adventuretime.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import isa.adventuretime.Entity.Review;
import isa.adventuretime.Repository.ReviewRepo;

@Service
public class ReviewService{
    private ReviewRepo reviewRepo;

    @Autowired
    public ReviewService(ReviewRepo aR){
        this.reviewRepo = aR;
    }

    public  Review getById(Long id){
        return reviewRepo.getById(id);
    }

}
