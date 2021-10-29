package isa.adventuretime.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import isa.adventuretime.Entity.RequestForAdmin;
import isa.adventuretime.Repository.RequestForAdminRepo;

@Service
public class RequestForAdminService {
    private RequestForAdminRepo requestForAdminRepo;

    @Autowired
    public RequestForAdminService(RequestForAdminRepo repo){
        requestForAdminRepo = repo;
    }

    public RequestForAdmin getById(Long Id){
        return requestForAdminRepo.getById(Id);
    }
}
