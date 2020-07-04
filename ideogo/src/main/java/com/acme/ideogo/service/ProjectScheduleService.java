package com.acme.ideogo.service;

import com.acme.ideogo.model.MTask;
import com.acme.ideogo.model.ProjectSchedule;
import com.acme.ideogo.model.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import com.acme.ideogo.model.Project;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;


public interface ProjectScheduleService {
    ProjectSchedule getProjectScheduleById(Long projectScheduleId);
    ProjectSchedule getProjectScheduleByIdAndProjectId(Long projectId, Long projectScheduleId);
    ProjectSchedule createProjectSchedule(ProjectSchedule projectSchedule);
    ProjectSchedule updateProjectSchedule(Long projectScheduleId, ProjectSchedule projectScheduleRequest);
    ResponseEntity<?> deleteProjectSchedule(Long projectScheduleId);
}
