package com.acme.ideogo.service;

import com.acme.ideogo.model.Appointment;
import com.acme.ideogo.model.Skill;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

public interface AppointmentService {
    Page<Appointment> getAllAppointmentsByProjectScheduleId(Long projectScheduleId, Pageable pageable);
    Appointment createAppointment(Long projectScheduleId, Appointment appointment);
    Appointment updateAppointment(Long projectScheduleId, Long appointmentId, Appointment appointmentDetails);
    ResponseEntity<?> deleteAppointment(Long projectScheduleId, Long appointmentId);
    Appointment getAppointmentsByIdAndTagId(Long projectScheduleId, Long appointmentId);
}
