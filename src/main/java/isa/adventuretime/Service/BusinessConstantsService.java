package isa.adventuretime.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import isa.adventuretime.Entity.BusinessConstants;
import isa.adventuretime.Repository.BusinessConstantsRepo;

@Service
public class BusinessConstantsService{
    private BusinessConstantsRepo businessConstantsRepo;

    @Autowired
    public BusinessConstantsService(BusinessConstantsRepo aR){
        this.businessConstantsRepo = aR;
    }

    public  BusinessConstants getById(Long id){
        return businessConstantsRepo.getById(id);
    }

}
