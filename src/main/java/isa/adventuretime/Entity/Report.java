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
public class Report {
    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	protected Long id;

    Long registeredUserId;
    Boolean userShowedUp;
    String reportText;
    HeadEntityEnum forType;
    Long bookingId;

    public Report(){
        
    }

    public Report(Long registeredUserId, Boolean userShowedUp, String reportText, HeadEntityEnum forType, Long bookingId){
        this.registeredUserId = registeredUserId;
        this.userShowedUp = userShowedUp;
        this.reportText = reportText;
        this.forType = forType;
        this.bookingId = bookingId;
    }

}
