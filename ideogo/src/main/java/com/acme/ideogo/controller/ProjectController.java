package com.acme.ideogo.controller;


import com.acme.ideogo.model.Category;
import com.acme.ideogo.model.Project;
import com.acme.ideogo.resource.ProfileResource;
import com.acme.ideogo.resource.ProjectResource;;
import com.acme.ideogo.resource.SaveProfileResource;
import com.acme.ideogo.resource.SaveProjectResource;
import com.acme.ideogo.service.ProjectService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
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

@Tag(name = "projects", description = "the Projects API")
@RestController
@CrossOrigin(origins = "*", methods= {RequestMethod.GET,RequestMethod.POST,RequestMethod.DELETE,RequestMethod.PUT})

@RequestMapping("/api")
public class ProjectController {

    @Autowired
    private ModelMapper mapper;

    @Autowired
    private ProjectService projectService;

    @Operation(summary = "Get Projects", description = "Get All Projects by Pages", tags = { "projects" })
    @GetMapping("/projects")
    public Page<ProjectResource> getAllProjects(
            @Parameter(description="Pageable Parameter")
                    Pageable pageable) {
        Page<Project> projectsPage = projectService.getAllProjects(pageable);
        List<ProjectResource> resources = projectsPage.getContent().stream().map(this::convertToResource).collect(Collectors.toList());
        return new PageImpl<>(resources, pageable, resources.size());
    }

    @Operation(summary = "Get Project by Id", description = "Get a Project by specifying Id", tags = { "projects" })
    @GetMapping("/projects/{projectid}")
    public ProjectResource getProjectById(
            @Parameter(description="Project Id")
            @PathVariable(name = "id") Long projectId) {
        return convertToResource(projectService.getProjectById(projectId));
    }

    @PostMapping("/leader/{leaderId}/projects")
    public ProjectResource createProject(@PathVariable(name = "leaderId") Long userId,
                                         @Valid @RequestBody SaveProjectResource resource) {
        return convertToResource(projectService.createProject(userId, convertToEntity(resource)));
    }

    @PutMapping("/leader/{leaderId}/projects/{projectid}")
    public ProjectResource updateProject(@PathVariable(name = "leaderId") Long userId,
                                         @PathVariable(name = "projectId") Long projectId,
                                         @Valid @RequestBody SaveProjectResource resource) {
        return convertToResource(projectService.updateProject(userId, projectId, convertToEntity(resource)));
    }

    @DeleteMapping("/leader/{leaderId}/projects/{projectid}")
    public ResponseEntity<?> deleteProject(@PathVariable(name = "leaderId") Long userId,
                                           @PathVariable(name = "projectId") Long projectId) {
        return projectService.deleteProject(userId, projectId);
    }

    @GetMapping("/tags/{tagId}/projects")
    public Page<ProjectResource> getAllProjectsByTagId(@PathVariable(name = "projectId") Long projectId, Pageable pageable) {
        Page<Project> projectsPage = projectService.getAllProjectsByTagId(projectId, pageable);
        List<ProjectResource> resources = projectsPage.getContent().stream().map(this::convertToResource).collect(Collectors.toList());
        return new PageImpl<>(resources, pageable, resources.size());
    }


    @PostMapping("/projects/{projectid}/users/{userId}")
    public ProjectResource assignProjectTag(@PathVariable(name = "projectId") Long projectId,
                                            @PathVariable(name = "tagId") Long tagId) {
        return convertToResource(projectService.assignProjectUser(projectId, tagId));
    }

    @DeleteMapping("/projects/{projectid}/users/{userId}")
    public ProjectResource unassignProjectTag(@PathVariable(name = "projectId") Long projectId,
                                              @PathVariable(name = "tagId") Long tagId) {

        return convertToResource(projectService.unassignProjectUser(projectId, tagId));
    }


    private Project convertToEntity(SaveProjectResource resource) {
        return mapper.map(resource, Project.class);
    }

    private ProjectResource convertToResource(Project entity) {
        return mapper.map(entity, ProjectResource.class);
    }

}
