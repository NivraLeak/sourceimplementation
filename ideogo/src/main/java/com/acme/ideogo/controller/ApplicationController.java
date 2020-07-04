package com.acme.ideogo.controller;

import com.acme.ideogo.model.Application;
import com.acme.ideogo.resource.ApplicationResource;
import com.acme.ideogo.resource.SaveApplicationResource;
import com.acme.ideogo.service.ApplicationService;
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

@Tag(name = "applications", description = "the Applications API")
@RestController
@CrossOrigin(origins = "*", methods= {RequestMethod.GET,RequestMethod.POST,RequestMethod.DELETE,RequestMethod.PUT})

@RequestMapping("/api")
public class ApplicationController {
    @Autowired
    private ModelMapper mapper;
    @Autowired
    private ApplicationService applicationService;

    @GetMapping("/users/{userId}/applications")
    public Page<ApplicationResource> getAllApplicationsByUserId(
            @PathVariable(name = "userId") Long userId,
            Pageable pageable) {
        Page<Application> applicationPage = applicationService.getAllApplicationsByUserId(userId, pageable);
        List<ApplicationResource> resources = applicationPage.getContent().stream().map(this::convertToResource).collect(Collectors.toList());
        return new PageImpl<>(resources, pageable, resources.size());
    }

    //@GetMapping("/projects/{projectId}/applications/{applicationId}")
    //public ApplicationResource getApplicationByIdAndProjectId(@PathVariable(name = "projectId") Long projectId,
    //                                                       @PathVariable(name = "applicationId") Long applicationId) {
    //    return convertToResource(applicationService.getApplicationByIdAndProjectId(projectId, applicationId));
    //}

     @PostMapping("/users/{userId}/applications")
     public ApplicationResource createApplication(@PathVariable(name = "userId") Long userId,
                                          @Valid @RequestBody SaveApplicationResource resource) {
         return convertToResource(applicationService.createApplication(userId, convertToEntity(resource)));

     }




    @PutMapping("/users/{userId}/applications/{applicationId}")
    public ApplicationResource updateApplication(@PathVariable(name = "userId") Long userId,
                                         @PathVariable(name = "applicationId") Long applicationId,
                                         @Valid @RequestBody SaveApplicationResource resource) {
        return convertToResource(applicationService.updateApplication(userId, applicationId, convertToEntity(resource)));
    }

    @DeleteMapping("/users/{userId}/applications/{applicationId}")
    public ResponseEntity<?> deleteApplication(@PathVariable(name = "userId") Long userId,
                                           @PathVariable(name = "applicationId") Long applicationId) {
        return applicationService.deleteApplication(userId, applicationId);
    }

    private Application convertToEntity(SaveApplicationResource resource) {
        return mapper.map(resource, Application.class);
    }

    private ApplicationResource convertToResource(Application entity) {
        return mapper.map(entity, ApplicationResource.class);
    }





}
