package com.dh.SessionBookingSystem.controller.managePatients;

import com.dh.SessionBookingSystem.controller.manageDentists.FindByDentistController;
import com.dh.SessionBookingSystem.entity.Patient;
import com.dh.SessionBookingSystem.exception.ResourceNotFoundException;
import com.dh.SessionBookingSystem.service.PatientService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/findPatient")

public class FindByPatientController {

    private PatientService patientService;
    private final Logger LOGGER = Logger.getLogger(FindByPatientController.class);

    @Autowired
    public FindByPatientController(PatientService patientService) {
        this.patientService = patientService;
    }

    @GetMapping("/byId")
    public ResponseEntity<Optional<Patient>> findPatientById(@RequestParam Long id) throws ResourceNotFoundException {
        LOGGER.info("Request send: you are trying to find a Patient.");
        Optional<Patient> searchPatient = patientService.findById(id);
        if (searchPatient.isEmpty()) {
            throw new ResourceNotFoundException("Can't find a patient who does not exist in the database.");
        }
        return ResponseEntity.status(200).body(searchPatient);
    }

}
