package com.dh.SessionBookingSystem.service;

import com.dh.SessionBookingSystem.dto.AppointmentDTO;
import com.dh.SessionBookingSystem.entity.Appointment;
import com.dh.SessionBookingSystem.entity.Dentist;
import com.dh.SessionBookingSystem.entity.Patient;
import com.dh.SessionBookingSystem.exception.BadRequestException;
import com.dh.SessionBookingSystem.exception.ResourceNotFoundException;
import com.dh.SessionBookingSystem.repository.AppointmentRepository;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AppointmentService {

    private AppointmentRepository appointmentRepository;
    private final Logger LOGGER = Logger.getLogger(AppointmentService.class);

    @Autowired
    public AppointmentService(AppointmentRepository appointmentRepository) {
        this.appointmentRepository = appointmentRepository;
    }

    public AppointmentDTO save(AppointmentDTO appointmentDTO) throws BadRequestException {
        LOGGER.info("You are trying to add an Appointment with appointment date: " + appointmentDTO.getAppointmentDate() + ".");
        Appointment appointmentToSave = appointmentDTOToAppointment(appointmentDTO);
        Appointment appointmentSaved = appointmentRepository.save(appointmentToSave);
        LOGGER.info("Order completed. We are sending the result.");
        return appointmentToAppointmentDTO(appointmentSaved);
    }

    public Optional<AppointmentDTO> findById(Long id) throws ResourceNotFoundException {
        Optional<Appointment> searchAppointment = appointmentRepository.findById(id);
        LOGGER.info("We have received your request. Please wait.");
        if (searchAppointment.isEmpty()) {
            throw new ResourceNotFoundException("Don't find any appointment with id: " + id + ". Try again.");
        }
        return Optional.of(appointmentToAppointmentDTO(searchAppointment.get()));
    }

    public List<AppointmentDTO> findAll() throws ResourceNotFoundException {
        LOGGER.info("We have received your request. Please wait.");
        List<Appointment> appointmentList = appointmentRepository.findAll();
        List<AppointmentDTO> response = new ArrayList<>();
        for (Appointment appointment:appointmentList) {
            response.add(appointmentToAppointmentDTO(appointment));
        }
        return response;
    }

    public void update(AppointmentDTO appointmentDTO) throws BadRequestException, ResourceNotFoundException {
        LOGGER.info("We have received your request. Please wait.");
        Appointment appointmentToUpdate = appointmentDTOToAppointment(appointmentDTO);
        appointmentRepository.save(appointmentToUpdate);
        LOGGER.info("Order completed. We are sending the result.");
    }

    public void deleteById(Long id) throws ResourceNotFoundException {
        LOGGER.info("We have received your request. Please wait.");
        appointmentRepository.deleteById(id);
        LOGGER.info("Order completed. We are sending the result.");
    }

    private AppointmentDTO appointmentToAppointmentDTO(Appointment appointment) {

        AppointmentDTO response = new AppointmentDTO();

        response.setId(appointment.getId());
        response.setAppointmentDate(appointment.getAppointmentDate());
        response.setDentistId(appointment.getDentist().getId());
        response.setPatientId(appointment.getPatient().getId());

        return response;

    }

    private Appointment appointmentDTOToAppointment(AppointmentDTO appointmentDTO) {

        Appointment appointment = new Appointment();
        Patient patient = new Patient();
        Dentist dentist = new Dentist();

        patient.setId(appointmentDTO.getPatientId());
        dentist.setId(appointmentDTO.getDentistId());
        appointment.setId(appointmentDTO.getId());
        appointment.setAppointmentDate(appointmentDTO.getAppointmentDate());

        appointment.setPatient(patient);
        appointment.setDentist(dentist);

        return appointment;

    }

}
