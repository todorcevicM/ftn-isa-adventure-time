package isa.adventuretime.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import isa.adventuretime.Entity.Report;

@Repository
public interface ReportRepo extends JpaRepository<Report, Long> {

}
