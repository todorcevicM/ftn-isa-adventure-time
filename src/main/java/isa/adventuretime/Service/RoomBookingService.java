package isa.adventuretime.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import isa.adventuretime.Repository.RoomBookingRepo;

@Service
public class RoomBookingService {
    @Autowired
    private RoomBookingRepo roomBookingRepo;

}
