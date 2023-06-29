package com.dh.SessionBookingSystem.controller.manageDentists;

import com.dh.SessionBookingSystem.controller.manageAppointments.UpdateAppointmentController;
import com.dh.SessionBookingSystem.entity.Dentist;
import com.dh.SessionBookingSystem.exception.BadRequestException;
import com.dh.SessionBookingSystem.exception.ResourceNotFoundException;
import com.dh.SessionBookingSystem.service.DentistService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/updateDentist")
public class UpdateDentistController {

    private DentistService dentistService;
    private final Logger LOGGER = Logger.getLogger(UpdateDentistController.class);

    @Autowired
    public UpdateDentistController(DentistService dentistService) {
        this.dentistService = dentistService;
    }

    @PutMapping
    public void update(@RequestBody Dentist dentist) throws ResourceNotFoundException, BadRequestException {

        LOGGER.info("Request send: you are trying to update a dentist with name: " + dentist.getName() + ".");
        Optional<Dentist> searchDentist = dentistService.findById(dentist.getId());
        if (searchDentist.isEmpty()) {
            throw new ResourceNotFoundException("Can't update a dentist who does not exist in the database.");
        }
        dentistService.update(dentist);
        LOGGER.info("Update dentist with id: " + dentist.getId());
    }

}