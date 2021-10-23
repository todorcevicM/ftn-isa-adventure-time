package isa.adventuretime.Entity;


import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Adventure {
    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

    private String name;
    private String address;
    private double GeoLng;
    private double GeoLat; 
    private String promoDescription; // prethodno samo description
    private Long instructorId;
    private String instructorBio;

    private String equipment; //ovo mozda prebaciti u termin avanture
    private String extraService; 

    // sta je extraService a sta priceAndInfo, i ako se zaista razlikuju zasto nema i jedno i drugo u Boat klasi


    private String rules;
    private String priceAndInfo;
    private int percentTakenIfCanceled;

    private Date reservationStart;
    private Date reservationEnd;
    private String location;
    private int duration;
    private int maxUsers;    
    // todo: dodatne usluge, obzirom da se dodaju kao tagovi, mozda liste koristiti ili nesto tako
    private double pricePerDay;

}
