package com.dh.SessionBookingSystem.service;

import com.dh.SessionBookingSystem.entity.Dentist;
import com.dh.SessionBookingSystem.exception.BadRequestException;
import com.dh.SessionBookingSystem.exception.ResourceNotFoundException;
import com.dh.SessionBookingSystem.repository.DentistRepository;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DentistService {

    private DentistRepository dentistRepository;
    private final Logger LOGGER = Logger.getLogger(DentistService.class);

    @Autowired
    public DentistService(DentistRepository dentistRepository) {
        this.dentistRepository = dentistRepository;
    }

    public Dentist save(Dentist dentist) throws BadRequestException {
        LOGGER.info("We have received your request. Please wait.");
        dentistRepository.save(dentist);
        LOGGER.info("Order completed. We are sending the result.");
        return dentist;
    }

    public Optional<Dentist> findById(Long id) throws ResourceNotFoundException {
        LOGGER.info("We have received your request. Please wait.");
        return dentistRepository.findById(id);
    }

    public List<Dentist> findAll() throws ResourceNotFoundException {
        LOGGER.info("We have received your request. Please wait.");
        return dentistRepository.findAll();
    };

    public void update(Dentist dentist) throws BadRequestException, ResourceNotFoundException {
        LOGGER.info("We have received your request. Please wait.");
        dentistRepository.save(dentist);
        LOGGER.info("Order completed. We are sending the result.");
    }
    public void deleteById(Long id) throws ResourceNotFoundException {
        LOGGER.info("We have received your request. Please wait.");
        dentistRepository.deleteById(id);
        LOGGER.info("Order completed. We are sending the result.");
    }

}
