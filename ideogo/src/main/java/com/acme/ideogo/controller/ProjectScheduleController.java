package com.acme.ideogo.controller;

import com.acme.ideogo.model.ProjectSchedule;
import com.acme.ideogo.resource.ProjectScheduleResource;
import com.acme.ideogo.resource.SaveProjectScheduleResource;
import com.acme.ideogo.service.ProjectScheduleService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Tag(name = "projectSchedules", description = "the ProjectSchedules API")
@RestController
@CrossOrigin(origins = "*", methods= {RequestMethod.GET,RequestMethod.POST,RequestMethod.DELETE,RequestMethod.PUT})

@RequestMapping("/api")
public class ProjectScheduleController {
    @Autowired
    private ModelMapper mapper;

    @Autowired
    private ProjectScheduleService projectScheduleService;

    @Operation(summary = "Get ProjectSchedule by Id", description = "Get a ProjectSchedule by specifying Id", tags = { "projectSchedule" })

    @GetMapping("/projectSchedules/{id}")
    public ProjectScheduleResource getProjectScheduleById(
            @Parameter(description="Project Id")
            @PathVariable(name = "id") Long projectScheduleId) {
        return convertToResource(projectScheduleService.getProjectScheduleById(projectScheduleId));
    }

    @PostMapping("/project/projectSchedule")
    public ProjectScheduleResource createProjectSchedule(@Valid @RequestBody SaveProjectScheduleResource resource)  {
        ProjectSchedule projectSchedule = convertToEntity(resource);
        return convertToResource(projectScheduleService.createProjectSchedule(projectSchedule));
    }

    @PutMapping("/project/projectSchedule/{id}")
    public ProjectScheduleResource updateProjectSchedule(@PathVariable(name = "id") Long projectId, @Valid @RequestBody SaveProjectScheduleResource resource) {
        ProjectSchedule projectSchedule = convertToEntity(resource);
        return convertToResource(projectScheduleService.updateProjectSchedule(projectId, projectSchedule));
    }

    @DeleteMapping("/project/projectSchedule/{id}")
    public ResponseEntity<?> deleteProjectSchedule(@PathVariable(name = "id") Long projectScheduleId) {
        return projectScheduleService.deleteProjectSchedule(projectScheduleId);
    }

    @GetMapping("/project/{projectId}/projectSchedule/{projectScheduleId}")
    public ProjectScheduleResource getProjectSchedulesByProjectId(@PathVariable(name = "projectId") Long projectId,
                                                                  @PathVariable(name = "projectScheduleId") Long projectScheduleId) {
        return convertToResource(projectScheduleService.getProjectScheduleByIdAndProjectId(projectId,projectScheduleId));
    }

    private ProjectSchedule convertToEntity(SaveProjectScheduleResource resource) {
        return mapper.map(resource, ProjectSchedule.class);
    }

    private ProjectScheduleResource convertToResource(ProjectSchedule entity) {
        return mapper.map(entity, ProjectScheduleResource.class);
    }

}
