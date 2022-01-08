package isa.adventuretime.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.RequestEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import isa.adventuretime.Entity.HeadEntityEnum;
import isa.adventuretime.Entity.Subscription;
import isa.adventuretime.Service.SubscriptionService;

@RestController
@RequestMapping("/api/subscription")
public class SubscriptionController {
    @Autowired
    SubscriptionService subscriptionService;

    //sub i unsub
    @PostMapping(path = "/subscribe")
    public Boolean subscribe(RequestEntity<String> subParam){
        //ako nije vec sub
        System.out.println(subParam.getBody());
        String split[] = subParam.getBody().split(";");
        Long subber = Long.parseLong(split[0]);
        Long subbed = Long.parseLong(split[1]);
        HeadEntityEnum forType = HeadEntityEnum.ADVENTURE;
        switch (split[2]) {
            case "ADVENTURE":
                forType = HeadEntityEnum.ADVENTURE;
                break;
            case "BOAT":
                forType = HeadEntityEnum.BOAT;
                break;
            case "COTTAGE":
                forType = HeadEntityEnum.COTTAGE;
                break;
            default:
                System.out.println("Something went wrong, >>> " + split[2]);
                return null;
        }
        if (subscriptionService.existsBySubberIdAndSubbedIdAndForEntity(subber, subbed, forType)){
            System.out.println("There is such subscription");
            return false;
        }
        subscriptionService.save(new Subscription(subber, subbed, forType));
        return true;
    }

    @PostMapping(path = "/unsubscribe")
    public Boolean unsubscribe(RequestEntity<String> subParam){
        String split[] = subParam.getBody().split(",");
        Long subber = Long.parseLong(split[0].split("\"")[2].split(":")[1]);
        Long subbed = Long.parseLong(split[1].split("\"")[2].split(":")[1]);
        String type = split[2].split("\"")[3];
        
        HeadEntityEnum forType = HeadEntityEnum.ADVENTURE;
        switch (type) {
            case "ADVENTURE":
                forType = HeadEntityEnum.ADVENTURE;
                break;
            case "BOAT":
                forType = HeadEntityEnum.BOAT;
                break;
            case "COTTAGE":
                forType = HeadEntityEnum.COTTAGE;
                break;
            default:
                System.out.println("Something went wrong, >>> " + split[2]);
                return null;
        }
        Subscription subscription = subscriptionService.getBySubberIdAndSubbedIdAndForEntity(subber, subbed, forType); 
        if (subscription == null){
            System.out.println("There is no such subscription");
            return false;
        }
        subscriptionService.delete(subscription);
        return true;
    }

    @PostMapping(path = "/existsSubscription")
    public Boolean existSubsricption(RequestEntity<String> subParam){
        //ako nije vec sub
        System.out.println(subParam.getBody());
        String split[] = subParam.getBody().split(";");
        Long subber = Long.parseLong(split[0]);
        Long subbed = Long.parseLong(split[1]);
        HeadEntityEnum forType = HeadEntityEnum.ADVENTURE;
        switch (split[2]) {
            case "ADVENTURE":
                forType = HeadEntityEnum.ADVENTURE;
                break;
            case "BOAT":
                forType = HeadEntityEnum.BOAT;
                break;
            case "COTTAGE":
                forType = HeadEntityEnum.COTTAGE;
                break;
            default:
                System.out.println("Something went wrong, >>> " + split[2]);
                return null;
        }
        if(subscriptionService.existsBySubberIdAndSubbedIdAndForEntity(subber, subbed, forType))
            return true;
        return false;
    }

}
