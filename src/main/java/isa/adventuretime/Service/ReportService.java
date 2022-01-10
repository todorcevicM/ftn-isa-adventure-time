package isa.adventuretime.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import isa.adventuretime.Entity.Report;
import isa.adventuretime.Repository.ReportRepo;

@Service
public class ReportService {
    @Autowired
    ReportRepo reportRepo;

    public Report save(Report report){
        return reportRepo.save(report);
    } 

}
