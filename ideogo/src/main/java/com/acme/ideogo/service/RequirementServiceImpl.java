package com.acme.ideogo.service;

import com.acme.ideogo.exception.ResourceNotFoundException;
import com.acme.ideogo.model.Requirement;
import com.acme.ideogo.model.Resource;
import com.acme.ideogo.repository.ProjectRepository;
import com.acme.ideogo.repository.RequirementRepository;
import com.acme.ideogo.repository.ResourceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class RequirementServiceImpl implements RequirementService{
    @Autowired
    private RequirementRepository requirementRepository;
    @Autowired
    private ProjectRepository projectRepository;

    @Override
    public Page<Requirement> getAllRequirementsByProjectId(Long projectId, Pageable pageable) {
        return requirementRepository.findByProjectId(projectId, pageable);
    }

    @Override
    public Requirement getRequirementByIdAndProjectId(Long projectId, Long requirementId) {
        return requirementRepository.findByIdAndProjectId(requirementId, projectId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "requirement not found with Id " + requirementId +
                                " and projectId " + projectId));
    }

    @Override
    public Requirement createRequirement(Long projectId, Requirement requirement) {
        return projectRepository.findById(projectId).map(project -> {
            requirement.setProject(project);
            return requirementRepository.save(requirement);
        }).orElseThrow(() -> new ResourceNotFoundException(
                "project", "Id", projectId));

    }

    @Override
    public Requirement updateRequirement(Long projectId, Long requirementId, Requirement requirementDetails) {
        if(!projectRepository.existsById(projectId))
            throw new ResourceNotFoundException("Project", "Id", projectId);

        return requirementRepository.findById(requirementId).map(requirement -> {
            requirement.setName(requirementDetails.getName());
            requirement.setDescription(requirementDetails.getDescription());
            return requirementRepository.save(requirement);
        }).orElseThrow(() -> new ResourceNotFoundException("Requieremnt", "Id", requirementId));
    }

    @Override
    public ResponseEntity<?> deleteRequirement(Long ProjectId, Long requirementId) {
        return requirementRepository.findByIdAndProjectId(requirementId, ProjectId).map(requirement -> {
            requirementRepository.delete(requirement);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException(
                "requirement not found with Id " + requirementId + " and projectId " + ProjectId));
    }
}
