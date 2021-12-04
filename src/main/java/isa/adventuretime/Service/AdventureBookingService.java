package isa.adventuretime.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import isa.adventuretime.Repository.AdventureBookingRepo;

@Service
public class AdventureBookingService {
    @Autowired
    private AdventureBookingRepo adventureBookingRepo;
    
}
