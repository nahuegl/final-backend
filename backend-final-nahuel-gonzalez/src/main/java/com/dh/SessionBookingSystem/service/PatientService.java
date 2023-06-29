package com.dh.SessionBookingSystem.service;

import com.dh.SessionBookingSystem.entity.Patient;
import com.dh.SessionBookingSystem.exception.BadRequestException;
import com.dh.SessionBookingSystem.exception.ResourceNotFoundException;
import com.dh.SessionBookingSystem.repository.PatientRepository;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PatientService {
    private PatientRepository patientRepository;
    private final Logger LOGGER = Logger.getLogger(PatientService.class);

    @Autowired
    public PatientService(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    public Patient save(Patient patient) {
        LOGGER.info("We have received your request. Please wait.");
       patientRepository.save(patient);
       LOGGER.info("Order completed. We are sending the result.");
        return patient;
    }

    public Optional<Patient> findById(Long id) throws ResourceNotFoundException {
        LOGGER.info("We have received your request. Please wait.");
        return patientRepository.findById(id);
    }

    public List<Patient> findAll() throws ResourceNotFoundException {
        LOGGER.info("We have received your request. Please wait.");
        return patientRepository.findAll();
    }

    public void update(Patient patient) throws BadRequestException, ResourceNotFoundException {
        LOGGER.info("We have received your request. Please wait.");
        patientRepository.save(patient);
        LOGGER.info("Order completed. We are sending the result.");
    }

    public void deleteById(Long id) throws ResourceNotFoundException {
        LOGGER.info("We have received your request. Please wait.");
        patientRepository.deleteById(id);
        LOGGER.info("Order completed. We are sending the result.");
    }


}
