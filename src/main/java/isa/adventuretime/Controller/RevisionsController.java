package isa.adventuretime.Controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import isa.adventuretime.Entity.Revision;
import isa.adventuretime.Service.RevisionsService;

@RestController
@RequestMapping(path = "/api/revision")
public class RevisionsController {
    @Autowired 
    private RevisionsService revisionsService;

    @RequestMapping(value = "/getAll")
    public ResponseEntity<ArrayList<Revision>> getAll() {
        return new ResponseEntity<>(revisionsService.getAll(), HttpStatus.OK);
    }

    @RequestMapping(value = "/getAllNotDeniedNotApproved")
    public ResponseEntity<ArrayList<Revision>> getAllNotDeniedNotApproved() {
        return new ResponseEntity<>(revisionsService.findAllByNotDeniedAndNotApproved(), HttpStatus.OK);
    }

    @PostMapping(value = "/approveRevision/{id}")
    public Boolean approveRevision(@PathVariable("id") Long id) {
        Revision revision = revisionsService.getById(id);
        revision.setApproved(true);
        revisionsService.save(revision);

        return true;
    }

    @PostMapping(value = "/denyRevision/{id}")
    public Boolean denyRevision(@PathVariable("id") Long id) {
        Revision revision = revisionsService.getById(id);
        revision.setDenied(true);
        revisionsService.save(revision);

        return true;
    }


    // TODO: da spojim postojeci revision sa korisnikom koji je napravio tu rezervaciju 

}
