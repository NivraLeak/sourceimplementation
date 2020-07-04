package com.acme.ideogo.service;

import com.acme.ideogo.exception.ResourceNotFoundException;
import com.acme.ideogo.model.ProjectSchedule;
import com.acme.ideogo.repository.ProjectRepository;
import com.acme.ideogo.repository.ProjectScheduleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


@Service
public class ProjectScheduleServiceImpl implements ProjectScheduleService{

    @Autowired
    ProjectScheduleRepository projectScheduleRepository;

    @Override
    public ProjectSchedule getProjectScheduleById(Long projectScheduleId) {
        return projectScheduleRepository.findById(projectScheduleId)
                .orElseThrow(() -> new ResourceNotFoundException("ProjectSchedule", "Id", projectScheduleId));
    }

    @Override
    public ProjectSchedule getProjectScheduleByIdAndProjectId(Long projectId, Long projectScheduleId) {
        return projectScheduleRepository.findByIdAndProjectId(projectId,projectScheduleId).orElseThrow(()->new ResourceNotFoundException("Project Schedule not found with id " + projectScheduleId +
                " and Project Id " + projectId));
    }

    @Override
    public ProjectSchedule createProjectSchedule(ProjectSchedule projectSchedule) {
        return projectScheduleRepository.save(projectSchedule);
    }

    @Override
    public ProjectSchedule updateProjectSchedule(Long projectScheduleId, ProjectSchedule projectScheduleRequest) {
        ProjectSchedule projectSchedule = projectScheduleRepository.findById(projectScheduleId)
                .orElseThrow(() -> new ResourceNotFoundException("ProjectSchedule", "Id", projectScheduleId));
        projectSchedule.setName(projectScheduleRequest.getName());
        return projectScheduleRepository.save(projectSchedule);
    }

    @Override
    public ResponseEntity<?> deleteProjectSchedule(Long projectScheduleId) {
        ProjectSchedule projectSchedule = projectScheduleRepository.findById(projectScheduleId)
                .orElseThrow(() -> new ResourceNotFoundException("Project Schedule", "Id", projectScheduleId));
        projectScheduleRepository.delete(projectSchedule);
        return ResponseEntity.ok().build();
    }
}
