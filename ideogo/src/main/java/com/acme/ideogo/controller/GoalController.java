package com.acme.ideogo.controller;

import com.acme.ideogo.model.Goal;
import com.acme.ideogo.resource.GoalResource;

import com.acme.ideogo.resource.SaveGoalResource;
import com.acme.ideogo.service.GoalService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Tag(name = "goals", description = "the Goals API")
@RestController
@CrossOrigin(origins = "*", methods= {RequestMethod.GET,RequestMethod.POST,RequestMethod.DELETE,RequestMethod.PUT})
@RequestMapping("/api")
public class GoalController {

    @Autowired
    private ModelMapper mapper;

    @Autowired
    private GoalService goalService;


    @Operation(summary = "Get Goal by Id", description = "Get a Goal by specifying Id", tags = { "goals" })
    @GetMapping("/goals/{id}")
    public GoalResource getGoalById(
            @Parameter(description="Goal Id")
            @PathVariable(name = "id") Long goalId) {
        return convertToResource(goalService.getGoalById(goalId));
    }

    @PostMapping("/goals")
    public GoalResource createGoal(@Valid @RequestBody SaveGoalResource resource)  {
        Goal goals = convertToEntity(resource);
        return convertToResource(goalService.createGoal(goals));
    }

    @PutMapping("/goals/{id}")
    public GoalResource updateGoal(@PathVariable(name = "id") Long goalId, @Valid @RequestBody SaveGoalResource resource) {
        Goal goal = convertToEntity(resource);
        return convertToResource(goalService.updateGoal(goalId, goal));
    }

    @DeleteMapping("/goals/{id}")
    public ResponseEntity<?> deleteGoaly(@PathVariable(name = "id") Long goalId) {
        return goalService.deleteGoal(goalId);
    }


    private Goal convertToEntity(SaveGoalResource resource) {
        return mapper.map(resource, Goal.class);
    }

    private GoalResource convertToResource(Goal entity) {
        return mapper.map(entity, GoalResource.class);
    }

}
