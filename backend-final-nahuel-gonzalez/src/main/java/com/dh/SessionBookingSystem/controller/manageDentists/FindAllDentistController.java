package com.dh.SessionBookingSystem.controller.manageDentists;

import com.dh.SessionBookingSystem.controller.manageAppointments.FindAllAppointmentController;
import com.dh.SessionBookingSystem.entity.Dentist;
import com.dh.SessionBookingSystem.exception.ResourceNotFoundException;
import com.dh.SessionBookingSystem.service.DentistService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/findAllDentist")

public class FindAllDentistController {

    private DentistService dentistService;
    private final Logger LOGGER = Logger.getLogger(FindAllDentistController.class);

    @Autowired
    public FindAllDentistController(DentistService dentistService) {
        this.dentistService = dentistService;
    }

    @GetMapping
    public List<Dentist> findAllDentist() throws ResourceNotFoundException {
        LOGGER.info("Request send: you are trying to list all dentist.");
        return dentistService.findAll();
    }
}
