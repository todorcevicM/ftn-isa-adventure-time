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
public class Cottage {
    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

    private String address;
    private Double GeoLng;
    private Double GeoLat; 
    private String description;
    private int numberOfRooms;
    private int numberOfBedsPerRoom; 
    private String rules;
    private float pricePerDay;
    private Date reservationStart;
    private Date reservationEnd;
    private Long ownerId;
    private String priceAndInfo;

}
