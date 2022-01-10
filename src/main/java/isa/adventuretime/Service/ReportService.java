package isa.adventuretime.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import isa.adventuretime.Entity.Report;
import isa.adventuretime.Repository.ReportRepo;

import isa.adventuretime.Repository.BoatBookingRepo;
import isa.adventuretime.Repository.AdventureBookingRepo;
import isa.adventuretime.Repository.RoomBookingRepo;

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
		adventureBookingRepo.setReportFlagToTrue(id);
	}

	public void setReportFlagToTrueBoat(Long id) {
		boatBookingRepo.setReportFlagToTrue(id);
	}

	public void setReportFlagToTrueRoom(Long id) {
		roomBookingRepo.setReportFlagToTrue(id);
	}
	/*
	 * ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣀⣤⣤⣤⣤⣤⣤⣤⣤⣄⣀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀
	 * ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⣴⣾⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣶⣤⡀⠀⠀⠀⠀⠀⠀⠀
	 * ⠀⠀⠀⠀⠀⠀⠀⠀⠀⢠⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣦⡀⠀⠀⠀⠀⠀
	 * ⠀⠀⠀⠀⠀⠀⠀⠀⢀⣾⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣷⠀⠀⠀⠀⠀
	 * ⠀⠀⠀⠀⠀⠀⠀⠀⣸⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⡿⠿⠿⠛⠛⠛⠛⠿⠿⣿⣿⣷⣄⠀⠀⠀
	 * ⠀⠀⠀⠀⠀⠀⠀⠀⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⡆⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠈⠻⣿⣷⠀⠀
	 * ⠀⠀⢀⣠⣤⣴⣶⣶⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢸⣿⣇⠀
	 * ⠀⢀⣾⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣶⣤⣤⣤⣤⣤⣤⣤⣤⣴⣶⣿⣿⡿⠀
	 * ⠀⢸⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⠃⠀
	 * ⠀⢸⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⠁⠀⠀
	 * ⠀⣸⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⠀⠀⠀
	 * ⠀⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⠀⠀⠀
	 * ⠀⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⠀⠀⠀
	 * ⠀⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⡿⠀⠀⠀
	 * ⠀⢿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⡇⠀⠀⠀
	 * ⠀⢸⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⡇⠀⠀⠀
	 * ⠀⠀⢿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⡿⠀⠀⠀⠀
	 * ⠀⠀⠀⠙⠿⠿⠿⠿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⠿⠿⣿⣿⣿⣿⣿⣿⣿⣿⣿⡇⠀⠀⠀⠀
	 * ⠀⠀⠀⠀⠀⠀⠀⠀⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⡄⠀⣿⣿⣿⣿⣿⣿⣿⣿⣿⡇⠀⠀⠀⠀
	 * ⠀⠀⠀⠀⠀⠀⠀⠀⢸⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣷⢐⣿⣿⣿⣿⣿⣿⣿⣿⣿⠃⠀⠀⠀⠀
	 * ⠀⠀⠀⠀⠀⠀⠀⠀⠀⢻⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣾⣿⣿⣿⣿⣿⣿⣿⣿⣿⠀⠀⠀⠀⠀
	 * ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢻⣿⣿⣿⡿⣟⣯⣿⠟⡉⠉⣿⣿⣿⣿⣿⣿⣿⣿⣿⠀⠀⠀⠀⠀
	 * ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠻⢿⣽⣿⣿⣿⠿⠿⠟⠒⠉⠉⠉⠉⠉⠉⠉⠙⠋⠀⠀⠀⠀⠀
	 * ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠈⠿⠋⠉⢀⣠⣤⣤⡔⣄⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀
	 * ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣴⠾⠛⠋⠉⠀⢀⣀⠐⣤⣶⣶⡤⢤⣤⠀⠀⠀⠀⠀⠀
	 * ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣤⣰⣶⣾⣿⣿⣿⣆⠀⣀⣀⡀⣀⡀⠀⠀⠀⠀⠀⠀
	 * ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠉⠉⠀⢀⢀⣀⠀⣀⣈⡿⠿⠿⠽⠃⠀⠀⠀⠀⠀⠀
	 * ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠛⠛⠿⠿⠿⠿⠾⠟⢁⣀⡴⣦⠆⠀⠀⠀⠀⠀⠀
	 * ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢦⣤⣀⣀⠀⠀⠀⠀⢘⣿⣍⡷⠆⠀⠀⠀⠀⠀⠀
	 * ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢶⣄⠈⠉⠛⠛⠿⠓⠀⠉⠋⠉⣀⠀⠀⠀⠀⠀⠀⠀
	 * ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣧⡀⠙⠻⢶⣶⡤⠀⠀⠛⠶⠾⠼⠋⠀⠀⠀⠀⠀⠀⠀
	 * ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣆⠈⠻⣶⣤⡀⠀⠀⢸⠿⣶⣦⣤⣠⣾⠀⠀⠀⠀⠀⠀⠀
	 * ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢠⠙⢷⣤⣀⠈⠁⠀⠀⢠⣤⣀⠈⠉⠈⠀⠀⠀⠀⠀⠀⠀⠀
	 * ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⡌⢧⣀⠉⠛⠃⠀⠀⠀⠀⠉⠛⠿⠿⠻⠃⠀⠀⠀⠀⠀⠀⠀
	 * ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠰⢳⣄⠙⠛⢋⠁⠀⠀⠀⠘⠿⣴⣤⣄⣤⡄⠀⠀⠀⠀⠀⠀⠀
	 * ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠘⣄⡙⠛⠋⠀⠀⠀⠀⠀⠰⣤⣀⠉⠉⠉⠀⠀⠀⠀⠀⠀⠀⠀
	 * ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⢠⡈⠉⠉⠀⠀⠀⠀⠀⠀⢀⡈⠙⠛⠛⠛⠁⠀⠀⠀⠀⠀⠀⠀
	 * ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠈⢦⡉⠛⡁⠀⠀⠀⠀⠀⠀⠈⠻⠷⣶⣦⡆⠀⠀⠀⠀⠀⠀⠀⠀
	 * ⠀⠀⠀⠀⠀⠀⠀⠀⢠⡈⢷⣌⠙⠛⠁⠀⠀⠀⠀⠀⠀⠰⣦⣄⣀⣀⡀⠀⠀⠀⠀⠀⠀⠀⠀
	 * ⠀⠀⠀⠀⠀⠀⠀⠀⠈⢷⣄⡉⠛⠛⠀⠀⠀⠀⠀⠀⠀⢀⠈⠙⠛⠛⠀⠀⠀⠀⠀⠀⠀⠀⠀
	 * ⠀⠀⠀⠀⠀⠀⠀⠀⢦⣀⠉⠛⠷⠖⠀⠀⠀⠀⠀⠀⠀⠘⠿⣶⣦⡄⠀⠀⠀⠀⠀⠀⠀⠀⠀
	 * ⠀⠀⠀⠀⠀⠀⠀⣠⣀⠙⠳⠶⠶⠀⠀⠀⠀⠀⠀⠀⠀⢠⣀⣀⣀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀
	 * ⠀⠀⠀⠀⠀⠀⠀⠙⠻⢿⣶⣤⣤⠀⠀⠀⠀⠀⠀⠀⢠⠛⠛⠻⠿⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀
	 * ⠀⠀⠀⠀⠀⠀⠀⠰⣦⣄⠈⠉⠋⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠈⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀
	 * ⠀⠀⠀⠀⠀⠀⠀⠀⢹⣿⣿⣶⡆⠀⠀⠀⠀⠀⠀⠀⠺⠿⠿⠿⠁⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀
	 * ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠙⠻⠟⠁⠀⠀⠀⠀⠀⠀⢀⣤⣤⣤⣤⡄⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀
	 * ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠈⣀⣀⣀⣀⠁⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀
	 * ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠛⠛⠻⠿⠿⠧⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀
	 * ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢠⣞⣻⣿⣿⣔⣿⠂⠀⠀⠀⠀⠀⠀⠀⠀⠀
	 */
}
