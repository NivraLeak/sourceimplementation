package com.acme.ideogo.controller;

import com.acme.ideogo.model.ProjectSchedule;
import com.acme.ideogo.model.Requirement;
import com.acme.ideogo.resource.ProjectScheduleResource;
import com.acme.ideogo.resource.RequirementResource;
import com.acme.ideogo.resource.SaveProjectScheduleResource;
import com.acme.ideogo.resource.SaveRequirementResource;
import com.acme.ideogo.service.RequirementService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@Tag(name = "requirements", description = "the Requirements API")
@RestController
@CrossOrigin(origins = "*", methods= {RequestMethod.GET,RequestMethod.POST,RequestMethod.DELETE,RequestMethod.PUT})

@RequestMapping("/api")
public class RequirementController {

    @Autowired
    private ModelMapper mapper;

    @Autowired
    private RequirementService requirementService;

    @GetMapping("/projects/{projectId}/requirements")
    public Page<RequirementResource> getAllRequirementsByProjectId(
            @PathVariable(name = "projectId") Long projectId,
            Pageable pageable) {
        Page<Requirement> commentPage = requirementService.getAllRequirementsByProjectId(projectId, pageable);
        List<RequirementResource> resources = commentPage.getContent().stream().map(this::convertToResource).collect(Collectors.toList());
        return new PageImpl<>(resources, pageable, resources.size());
    }

    @GetMapping("/projects/{projectId}/requirements/{requirementId}")
    public RequirementResource getCommentByIdAndProjectId(@PathVariable(name = "projectId") Long postId,
                                                   @PathVariable(name = "requirementId") Long commentId) {
        return convertToResource(requirementService.getRequirementByIdAndProjectId(postId, commentId));
    }

    @PostMapping("/projects/{projectId}/requirements")
    public RequirementResource createRequirement(@PathVariable(name = "projectId") Long requirementId,
                                             @Valid @RequestBody SaveRequirementResource resource) {
        return convertToResource(requirementService.createRequirement(requirementId, convertToEntity(resource)));

    }

    @PutMapping("/projects/{projectId}/requirements/{requirementId}")
    public RequirementResource updateComment(@PathVariable(name = "projectId") Long projectId,
                                         @PathVariable(name = "requirementId") Long requirementId,
                                         @Valid @RequestBody SaveRequirementResource resource) {
        return convertToResource(requirementService.updateRequirement(projectId, requirementId, convertToEntity(resource)));
    }

    @DeleteMapping("/projects/{projectId}/requirements/{requirementId}")
    public ResponseEntity<?> deleteComment(@PathVariable(name = "projectId") Long projectId,
                                           @PathVariable(name = "requirementId") Long requirementId) {
        return requirementService.deleteRequirement(projectId, requirementId);
    }

    private Requirement convertToEntity(SaveRequirementResource resource) {
        return mapper.map(resource, Requirement.class);
    }

    private RequirementResource convertToResource(Requirement entity) {
        return mapper.map(entity, RequirementResource.class);
    }
}
