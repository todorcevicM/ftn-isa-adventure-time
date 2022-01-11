package isa.adventuretime.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import isa.adventuretime.Entity.BoatBooking;
import isa.adventuretime.Entity.Report;
import isa.adventuretime.Entity.RoomBooking;
import isa.adventuretime.Repository.AdventureBookingRepo;
import isa.adventuretime.Repository.BoatBookingRepo;
import isa.adventuretime.Repository.ReportRepo;
import isa.adventuretime.Repository.RoomBookingRepo;
import isa.adventuretime.Entity.AdventureBooking;

@Service
public class ReportService {
	@Autowired
	ReportRepo reportRepo;
	@Autowired
	AdventureBookingRepo adventureBookingRepo;
	@Autowired
	BoatBookingRepo boatBookingRepo;
	@Autowired
	RoomBookingRepo roomBookingRepo;

	public Report save(Report report) {
		return reportRepo.save(report);
	}

	public void setReportFlagToTrueAdventure(Long id) {
		AdventureBooking aB = adventureBookingRepo.getById(id);
		aB.setReportMade(true);
		adventureBookingRepo.save(aB);
	}

	public void setReportFlagToTrueBoat(Long id) {
		BoatBooking bB = boatBookingRepo.getById(id);
		bB.setReportMade(true);
		boatBookingRepo.save(bB);
	}

	public void setReportFlagToTrueRoom(Long id) {
		RoomBooking rB = roomBookingRepo.getById(id);
		rB.setReportMade(true);
		roomBookingRepo.save(rB);
	}
}
