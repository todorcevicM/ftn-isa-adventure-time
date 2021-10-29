package isa.adventuretime.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import isa.adventuretime.Entity.Booking;
import isa.adventuretime.Repository.BookingRepo;

@Service
public class BookingService{
    private BookingRepo bookingRepo;

    @Autowired
    public BookingService(BookingRepo aR){
        this.bookingRepo = aR;
    }

    public  Booking getById(Long id){
        return bookingRepo.getById(id);
    }

}
