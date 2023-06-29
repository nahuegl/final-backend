package com.dh.SessionBookingSystem.controller.manageDentists;

import com.dh.SessionBookingSystem.controller.manageAppointments.FindByAppointmentController;
import com.dh.SessionBookingSystem.entity.Dentist;
import com.dh.SessionBookingSystem.exception.ResourceNotFoundException;
import com.dh.SessionBookingSystem.service.DentistService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/findDentist")

public class FindByDentistController {

    private DentistService dentistService;
    private final Logger LOGGER = Logger.getLogger(FindByDentistController.class);

    @Autowired
    public FindByDentistController(DentistService dentistService) {
        this.dentistService = dentistService;
    }

    @GetMapping("/byId")
    public ResponseEntity<Optional<Dentist>> findDentistById(@RequestParam Long id) throws ResourceNotFoundException {
        LOGGER.info("Request send: you are trying to find a Dentist.");
        Optional<Dentist> searchDentist = dentistService.findById(id);
        if (searchDentist.isEmpty()) {
            throw new ResourceNotFoundException("Can't find a dentist who does not exist in the database.");
        }
        return ResponseEntity.status(200).body(searchDentist);
    }

}
