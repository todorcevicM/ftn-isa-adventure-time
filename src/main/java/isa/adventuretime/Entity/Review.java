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
public class Review {
    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	protected Long id;
    
    private Long reviewerId;
    private Long reviewedId;
    private HeadEntityEnum forEntity; 
    private String reviewText;
    private boolean accepted;

}
