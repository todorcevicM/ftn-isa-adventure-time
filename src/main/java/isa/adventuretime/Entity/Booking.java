package isa.adventuretime.Entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Booking {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long bookedGoodsId;
    private Long registeredUserId;
    private boolean quickBooking;

    private Date start;
    private Date end;
    private double price; //if not default price
    private String extraService; // if any
    private int maxUsers; //if not default, is this needed?
    /*TODO odluciti da li je dovoljan 1 booking za camce i vikendice, 
    da li da skratimo model, da ne postoji vlasnik broda, vlasnik vikendice
    vec samo vlasnik, jer nema dodanih atributa za vlasnike kao ni reg korisnike, pecaros ima u sebi vreme njega bi trebalo izdvojiti
    da li i u booking i u boat treba max users
    */
}
