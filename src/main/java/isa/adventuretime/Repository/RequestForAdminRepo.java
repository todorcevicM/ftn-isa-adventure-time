package isa.adventuretime.Repository;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import isa.adventuretime.Entity.RequestForAdmin;
@Repository
public interface RequestForAdminRepo extends JpaRepository<RequestForAdmin, Long>{
    ArrayList<RequestForAdmin> findAll();
}
