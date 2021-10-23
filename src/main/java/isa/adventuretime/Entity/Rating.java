package isa.adventuretime.Entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Rating {
    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	protected Long id;
    
    private Long raterId;
    private Long ratedId;
    private HeadEntityEnum forEntity; 
    private int rating;

}