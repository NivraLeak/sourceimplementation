package com.acme.ideogo.controller;

import com.acme.ideogo.model.MTask;
import com.acme.ideogo.resource.MTaskResource;
import com.acme.ideogo.resource.SaveMTaskResource;
import com.acme.ideogo.service.MTaskService;
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

@Tag(name = "mTasks", description = "the MTasks API")
@RestController
@CrossOrigin(origins = "*", methods= {RequestMethod.GET,RequestMethod.POST,RequestMethod.DELETE,RequestMethod.PUT})

@RequestMapping("/api")
public class MTaskController {
    @Autowired
    private ModelMapper mapper;

    @Autowired
    private MTaskService mTaskService;

    @Operation(summary = "Get mTasks by Id", description = "Get a MTasks by specifying project Id", tags = { "MTasks" })
    @GetMapping("/projectschedules/{projectscheduleId}/mTasks")
    public Page<MTaskResource> getAllMTaskByProjectScheduleId(@PathVariable(name = "projectsId") Long projectsId, Pageable pageable) {
        Page<MTask> mTasksPage = mTaskService.getAllMTaskByProjectScheduleId(projectsId, pageable);
        List<MTaskResource> resources = mTasksPage.getContent().stream().map(this::convertToResource).collect(Collectors.toList());
        return new PageImpl<>(resources, pageable, resources.size());
    }

    @Operation(summary = "Get mTasks by Id", description = "Get a MTasks by specifying Id", tags = { "MTasks" })
    @GetMapping("/mTasks/{id}")
    public MTaskResource getMTaskById(
            @Parameter(description="Project Id")
            @PathVariable(name = "id") Long mTaskId) {
        return convertToResource(mTaskService.getMTaskById(mTaskId));
    }

    @PostMapping("/mTask")
    public MTaskResource createMTask(@Valid @RequestBody SaveMTaskResource resource)  {
        MTask mTask = convertToEntity(resource);
        return convertToResource(mTaskService.createMTask(mTask));
    }

    @PutMapping("/mTask/{id}")
    public MTaskResource updateMTask(@PathVariable(name = "id") Long projectId, @Valid @RequestBody SaveMTaskResource resource) {
        MTask mTask = convertToEntity(resource);
        return convertToResource(mTaskService.updateMTask(projectId, mTask));
    }

    @DeleteMapping("/mTask/{id}")
    public ResponseEntity<?> deleteMTask(@PathVariable(name = "id") Long mTaskId) {
        return mTaskService.deleteMTask(mTaskId);
    }

    private MTask convertToEntity(SaveMTaskResource resource) {
        return mapper.map(resource, MTask.class);
    }

    private MTaskResource convertToResource(MTask entity) {
        return mapper.map(entity, MTaskResource.class);
    }

}
