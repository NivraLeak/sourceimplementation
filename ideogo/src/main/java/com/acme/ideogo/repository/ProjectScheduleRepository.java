package com.acme.ideogo.repository;

import com.acme.ideogo.model.ProjectSchedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface ProjectScheduleRepository extends JpaRepository<ProjectSchedule,Long> {
    Optional<ProjectSchedule> findByIdAndProjectId(Long id, Long projectScheduleId);
}
