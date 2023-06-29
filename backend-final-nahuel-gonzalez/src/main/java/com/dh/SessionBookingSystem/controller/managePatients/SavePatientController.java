package com.dh.SessionBookingSystem.controller.managePatients;

import com.dh.SessionBookingSystem.controller.manageDentists.SaveDentistController;
import com.dh.SessionBookingSystem.entity.Patient;
import com.dh.SessionBookingSystem.exception.BadRequestException;
import com.dh.SessionBookingSystem.service.PatientService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/savePatient")

public class SavePatientController {

    private PatientService patientService;
    private final Logger LOGGER = Logger.getLogger(SavePatientController.class);

    @Autowired
    public SavePatientController(PatientService patientService) {
        this.patientService = patientService;
    }

    @PostMapping
    public void save(@RequestBody Patient patient) throws BadRequestException {

        LOGGER.info("Request send: you are trying to save a patient with name: " + patient.getName() + ".");
        patientService.save(patient);
        LOGGER.info("Add a patient with id: " + patient.getId() + ".");

    }

}
