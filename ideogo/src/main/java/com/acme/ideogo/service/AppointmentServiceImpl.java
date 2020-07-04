package com.acme.ideogo.service;

import com.acme.ideogo.exception.ResourceNotFoundException;
import com.acme.ideogo.model.Appointment;
import com.acme.ideogo.model.Skill;
import com.acme.ideogo.repository.AppointmentRepository;
import com.acme.ideogo.repository.ProjectScheduleRepository;
import com.acme.ideogo.repository.SkillRepository;
import com.acme.ideogo.repository.TagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class AppointmentServiceImpl implements AppointmentService {

    @Autowired
    private ProjectScheduleRepository projectScheduleRepository;
    @Autowired
    private AppointmentRepository appointmentRepository;

    @Override
    public Page<Appointment> getAllAppointmentsByProjectScheduleId(Long projectScheduleId, Pageable pageable) {
        return appointmentRepository.findByProjectScheduleId(projectScheduleId,pageable);
    }

    @Override
    public Appointment createAppointment(Long projectScheduleId, Appointment appointment) {
        return projectScheduleRepository.findById(projectScheduleId).map(projectSchedule -> {
            appointment.setProjectSchedule(projectSchedule);
            return appointmentRepository.save(appointment);
        }).orElseThrow(() -> new ResourceNotFoundException(
                "projectSchedule", "Id", projectScheduleId));
    }

    @Override
    public Appointment updateAppointment(Long projectScheduleId, Long appointmentId, Appointment appointmentDetails) {
        if(!projectScheduleRepository.existsById(projectScheduleId))
            throw new ResourceNotFoundException("projectSchedule", "Id", projectScheduleId);

        return appointmentRepository.findById(appointmentId).map(appointment -> {
            appointment.setDate_time(appointmentDetails.getDate_time());
            return appointmentRepository.save(appointment);
        }).orElseThrow(() -> new ResourceNotFoundException("Appointment", "Id", appointmentId));
    }

    @Override
    public ResponseEntity<?> deleteAppointment(Long projectScheduleId, Long appointmentId) {
        return appointmentRepository.findByIdAndProjectScheduleId(appointmentId, projectScheduleId).map(appointment -> {
            appointmentRepository.delete(appointment);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException(
                "Appointment not found with Id " + appointmentId + " and TagId " + projectScheduleId));
    }

    @Override
    public Appointment getAppointmentsByIdAndTagId(Long projectScheduleId, Long appointmentId) {
        return appointmentRepository.findByIdAndProjectScheduleId(appointmentId,projectScheduleId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Skill not found with Id " + appointmentId +
                                " and ProfileId " + projectScheduleId ));
    }
}
