package isa.adventuretime.Controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import isa.adventuretime.Entity.Report;
import isa.adventuretime.Service.ReportService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.RequestEntity;

@RestController
@RequestMapping(path = "/api/report")
public class ReportController {
    
    @Autowired
    ReportService reportService;

    @PostMapping(path = "/makeReport")
    public boolean makeReport(RequestEntity<Report> report){
        //TODO: report saving, entity update

        return false;
    }

}
