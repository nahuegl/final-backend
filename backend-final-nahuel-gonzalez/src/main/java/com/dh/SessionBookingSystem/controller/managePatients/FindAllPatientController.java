package com.dh.SessionBookingSystem.controller.managePatients;

import com.dh.SessionBookingSystem.controller.manageDentists.FindAllDentistController;
import com.dh.SessionBookingSystem.entity.Patient;
import com.dh.SessionBookingSystem.exception.ResourceNotFoundException;
import com.dh.SessionBookingSystem.service.PatientService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/findAllPatient")

public class FindAllPatientController {

    private PatientService patientService;
    private final Logger LOGGER = Logger.getLogger(FindAllPatientController.class);

    @Autowired
    public FindAllPatientController(PatientService patientService) { this.patientService = patientService; }

    @GetMapping
    public List<Patient> findAllDentist() throws ResourceNotFoundException {
        LOGGER.info("Request send: you are trying to list all patient.");
        return patientService.findAll();
    }
}
