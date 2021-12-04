package isa.adventuretime.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import isa.adventuretime.Entity.RegisteredUser;
import isa.adventuretime.Repository.RegisteredUserRepo;

@Service
public class RegisteredUserService {
    private RegisteredUserRepo registeredUserRepo;

    @Autowired
    public RegisteredUserService(RegisteredUserRepo repo){
        registeredUserRepo = repo;
    }

    public RegisteredUser getById(Long Id){
        return registeredUserRepo.getById(Id);
    }

    public RegisteredUser register(RegisteredUser user){
        return registeredUserRepo.save(user);
    }

    public RegisteredUser findByEmail(String email){
        return registeredUserRepo.findByEmail(email);
    }

    public RegisteredUser findByEmailHash(int code){
        return registeredUserRepo.findByEmailHash(code);
    }

    public RegisteredUser saveRegisteredUser(RegisteredUser registeredUser){
        return registeredUserRepo.save(registeredUser);
    }
}
