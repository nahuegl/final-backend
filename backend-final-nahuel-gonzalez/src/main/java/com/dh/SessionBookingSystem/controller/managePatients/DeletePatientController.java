package com.dh.SessionBookingSystem.controller.managePatients;

import com.dh.SessionBookingSystem.controller.manageDentists.DeleteDentistController;
import com.dh.SessionBookingSystem.entity.Patient;
import com.dh.SessionBookingSystem.exception.ResourceNotFoundException;
import com.dh.SessionBookingSystem.service.PatientService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/deletePatient")

public class DeletePatientController {

    private PatientService patientService;
    private final Logger LOGGER = Logger.getLogger(DeletePatientController.class);

    @Autowired
    public DeletePatientController(PatientService patientService) {
        this.patientService = patientService;
    }

    @DeleteMapping("/{id}")
    private void deletePatient(@PathVariable Long id) throws ResourceNotFoundException {

        LOGGER.info("Request send: you are trying to delete a patient with id: " + id + ".");
        Optional<Patient> searchPatient = patientService.findById(id);
        if (searchPatient.isEmpty()) {
            throw new ResourceNotFoundException("Can't delete a patient who does not exist in the database.");
        }
        patientService.deleteById(id);
        LOGGER.info("Delete patient with id: " + id);

    }

}