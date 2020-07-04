package com.acme.ideogo.controller;

import com.acme.ideogo.model.Activity;
import com.acme.ideogo.resource.ActivityResource;
import com.acme.ideogo.resource.SaveActivityResource;
import com.acme.ideogo.service.ActivityService;
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

@Tag(name = "activities", description = "the Activities API")
@RestController
@CrossOrigin(origins = "*", methods= {RequestMethod.GET,RequestMethod.POST,RequestMethod.DELETE,RequestMethod.PUT})

@RequestMapping("/api")
public class ActivityController {
    @Autowired
    private ModelMapper mapper;

    @Autowired
    private ActivityService activityService;

    @Operation(summary = "Get Activity by Id", description = "Get a Activity by specifying Id", tags = { "activities" })
    @GetMapping("/activities/{id}")
    public ActivityResource getActivityById(
            @Parameter(description="Project Id")
            @PathVariable(name = "id") Long activityId) {
        return convertToResource(activityService.getActivityById(activityId));
    }

    @PostMapping("/activity")
    public ActivityResource createActivity(@Valid @RequestBody SaveActivityResource resource)  {
        Activity activity = convertToEntity(resource);
        return convertToResource(activityService.createActivity(activity));
    }

    @PutMapping("/activity/{id}")
    public ActivityResource updateActivity(@PathVariable(name = "id") Long projectId, @Valid @RequestBody SaveActivityResource resource) {
        Activity activity = convertToEntity(resource);
        return convertToResource(activityService.updateActivity(projectId, activity));
    }

    @DeleteMapping("/activity/{id}")
    public ResponseEntity<?> deleteTag(@PathVariable(name = "id") Long activityId) {
        return activityService.deleteActivity(activityId);
    }

    @GetMapping("/projectSchedules/{projectScheduleId}/activities")
    public Page<ActivityResource> getAllActivitiesByProjectScheduleId(@PathVariable(name = "activityId") Long activityId, Pageable pageable) {
        Page<Activity> activitiesPage = activityService.getAllActivitiesByProjectScheduleId(activityId, pageable);
        List<ActivityResource> resources = activitiesPage.getContent().stream().map(this::convertToResource).collect(Collectors.toList());
        return new PageImpl<>(resources, pageable, resources.size());
    }

    private Activity convertToEntity(SaveActivityResource resource) {
        return mapper.map(resource, Activity.class);
    }

    private ActivityResource convertToResource(Activity entity) {
        return mapper.map(entity, ActivityResource.class);
    }
}
