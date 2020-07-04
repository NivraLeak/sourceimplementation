package com.acme.ideogo.service;

import com.acme.ideogo.model.Goal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.ResponseEntity;

public interface GoalService  {
    ResponseEntity<?> deleteGoal(Long goalId);
    Goal updateGoal(Long postId, Goal goalRequest);
    Goal createGoal(Goal post);
    Goal getGoalById(Long postId);
}
