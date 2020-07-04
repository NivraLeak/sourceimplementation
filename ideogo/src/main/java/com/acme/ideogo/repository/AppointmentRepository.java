package com.acme.ideogo.repository;

import com.acme.ideogo.model.Appointment;
import com.acme.ideogo.model.Skill;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface AppointmentRepository extends JpaRepository< Appointment,Long > {
    Page<Appointment> findByProjectScheduleId(Long appointmentId, Pageable pageable);
    Optional<Appointment> findByIdAndProjectScheduleId(Long id, Long appointmentId);
}
