package isa.adventuretime.Entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Boat {
    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

    private String name;

    private String type;
    private double length;
    private int engineNumber;
    private int enginePower;
    private int maxSpeed;

    private String navigationEquipment;

    private String address;
    private double geoLng;
    private double geoLat;

    private String promoDescription; 
    private String rules;
    private double pricePerDay;
    private Date reservationStart;
    private Date reservationEnd;
    private String priceAndInfo;
    private int maxUsers;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "owner_id", nullable = false)
    private BoatOwner boatOwner;
}
