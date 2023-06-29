package com.dh.SessionBookingSystem.controller.managePatients;

import com.dh.SessionBookingSystem.controller.manageDentists.UpdateDentistController;
import com.dh.SessionBookingSystem.entity.Patient;
import com.dh.SessionBookingSystem.exception.BadRequestException;
import com.dh.SessionBookingSystem.exception.ResourceNotFoundException;
import com.dh.SessionBookingSystem.service.PatientService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/updatePatient")

public class UpdatePatientController {

    private PatientService patientService;
    private final Logger LOGGER = Logger.getLogger(UpdatePatientController.class);

    @Autowired
    public UpdatePatientController(PatientService patientService) {
        this.patientService = patientService;
    }

    @PutMapping
    public void update(@RequestBody Patient patient) throws ResourceNotFoundException, BadRequestException {

        LOGGER.info("Request send: you are trying to update a patient with name: " + patient.getName() + "." );
        Optional<Patient> searchPatient = patientService.findById(patient.getId());
        if (searchPatient.isEmpty()) {
            throw new ResourceNotFoundException("Can't update a patient who does not exist in the database.");
        }
        patientService.update(patient);
        LOGGER.info("Update patient with id: " + patient.getId());
    }

}
