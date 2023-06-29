package com.dh.SessionBookingSystem.controller.manageDentists;

import com.dh.SessionBookingSystem.controller.manageAppointments.SaveApponintmentController;
import com.dh.SessionBookingSystem.entity.Dentist;
import com.dh.SessionBookingSystem.exception.BadRequestException;
import com.dh.SessionBookingSystem.service.DentistService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/saveDentist")

public class SaveDentistController {

    private DentistService dentistService;
    private final Logger LOGGER = Logger.getLogger(SaveDentistController.class);

    @Autowired
    public SaveDentistController(DentistService dentistService) {
        this.dentistService = dentistService;
    }

    @PostMapping
    public void save(@RequestBody Dentist dentist) throws BadRequestException {

        LOGGER.info("Request send: you are trying to save a dentist with name: " + dentist.getName() + ".");
        dentistService.save(dentist);
        LOGGER.info("Add a dentist with id: " + dentist.getId() + ".");

    }

}
