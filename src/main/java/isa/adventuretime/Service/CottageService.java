package isa.adventuretime.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import isa.adventuretime.Entity.Cottage;
import isa.adventuretime.Repository.CottageRepo;

@Service
public class CottageService {
    private CottageRepo CottageRepo;

    @Autowired
    public CottageService(CottageRepo aR){
        CottageRepo = aR;
    }

    public Cottage getById(Long Id){
        return CottageRepo.getById(Id);
    }
}
