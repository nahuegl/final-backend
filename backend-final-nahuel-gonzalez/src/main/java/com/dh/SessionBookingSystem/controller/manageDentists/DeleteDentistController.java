package com.dh.SessionBookingSystem.controller.manageDentists;

import com.dh.SessionBookingSystem.controller.manageAppointments.DeleteAppointmentController;
import com.dh.SessionBookingSystem.entity.Dentist;
import com.dh.SessionBookingSystem.exception.ResourceNotFoundException;
import com.dh.SessionBookingSystem.service.DentistService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/deleteDentist")

public class DeleteDentistController {

    private DentistService dentistService;
    private final Logger LOGGER = Logger.getLogger(DeleteDentistController.class);

    @Autowired
    public DeleteDentistController(DentistService dentistService) {
        this.dentistService = dentistService;
    }

    @DeleteMapping("/{id}")
    public void DeleteDentistById(@PathVariable Long id) throws ResourceNotFoundException {

        LOGGER.info("Request send: you are trying to delete a dentist with id: " + id + ".");
        Optional<Dentist> searchDentist = dentistService.findById(id);
        if (searchDentist.isEmpty()) {
           throw new ResourceNotFoundException("Can't delete a dentist who does not exist in the database.");
        }
        dentistService.deleteById(id);
        LOGGER.info("Delete dentist with id: " + id);

    }
}
