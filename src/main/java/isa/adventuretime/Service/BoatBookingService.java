package isa.adventuretime.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import isa.adventuretime.Repository.BoatBookingRepo;

@Service
public class BoatBookingService {
    @Autowired
    private BoatBookingRepo boatBookingRepo;

}
