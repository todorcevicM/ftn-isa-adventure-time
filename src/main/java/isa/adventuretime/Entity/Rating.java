package isa.adventuretime.Entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class Rating {
    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	protected Long id;
    
    private Long raterId;
    private Long ratedId;
    private HeadEntityEnum forEntity; 
    private int rating;

}