package isa.adventuretime.Controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import isa.adventuretime.Entity.Report;
import isa.adventuretime.Service.ReportService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.RequestEntity;

import isa.adventuretime.Entity.HeadEntityEnum;

@RestController
@RequestMapping(path = "/api/report")
public class ReportController {

	@Autowired
	ReportService reportService;

	@PostMapping(path = "/makeReport")
	public boolean makeReport(RequestEntity<String> report) {
		// TODO: Vidi da li se na reload stranice
		// kad se posalje report, gubi Make Report dugme
		String split[] = report.getBody().split(",");
		Long registeredUserId = Long.parseLong(split[0].split(":")[1]);
		Boolean userShowedUp = Boolean.parseBoolean(split[4].split(":")[1]);
		String reportText = split[2].split(":")[1];
		HeadEntityEnum forType = HeadEntityEnum.ADVENTURE;
		Long bookingId = Long.parseLong(split[3].split(":")[1]);
		switch (split[1].split(":")[1]) {
			case "\"ADVENTURE\"":
				reportService.setReportFlagToTrueAdventure(bookingId);
				break;
			case "\"BOAT\"":
				forType = HeadEntityEnum.BOAT;
				reportService.setReportFlagToTrueBoat(bookingId);
				break;
			case "\"COTTAGE\"":
				forType = HeadEntityEnum.COTTAGE;
				reportService.setReportFlagToTrueRoom(bookingId);
				break;
			default:
				System.out.println("Something went wrong >>> " + split[1].split(":")[1]);
				break;
		}

		Report newReport = new Report(registeredUserId, userShowedUp, reportText, forType, bookingId);
		reportService.save(newReport);
		System.out.println(report.getBody());

		return false;
	}

}
