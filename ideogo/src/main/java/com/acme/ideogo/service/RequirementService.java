package com.acme.ideogo.service;

import com.acme.ideogo.model.Requirement;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

public interface RequirementService {
    Page<Requirement> getAllRequirementsByProjectId(Long projectId, Pageable pageable);
    Requirement getRequirementByIdAndProjectId(Long projectId, Long requirementId);
    Requirement createRequirement(Long projectId, Requirement requirement);
    Requirement updateRequirement(Long projectId, Long requirementId, Requirement requirementDetails);
    ResponseEntity<?> deleteRequirement(Long ProjectId, Long requirementId);
}
