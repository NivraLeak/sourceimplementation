package com.acme.ideogo.service;

import com.acme.ideogo.exception.ResourceNotFoundException;
import com.acme.ideogo.model.Goal;
import com.acme.ideogo.model.Goal;
import com.acme.ideogo.model.Tag;
import com.acme.ideogo.repository.CategoryRepository;
import com.acme.ideogo.repository.GoalRepository;
import com.acme.ideogo.repository.TagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GoalServiceImpl implements GoalService {

    @Autowired
    private GoalRepository goalRepository;

    @Override
    public ResponseEntity<?> deleteGoal(Long goalId) {
        Goal goal = goalRepository.findById(goalId)
                .orElseThrow(() -> new ResourceNotFoundException("Goal", "Id", goalId));
        goalRepository.delete(goal);
        return ResponseEntity.ok().build();
    }

    @Override
    public Goal updateGoal(Long goalId, Goal goalRequest) {
        Goal goal = goalRepository.findById(goalId)
                .orElseThrow(() -> new ResourceNotFoundException("Goal", "Id", goalId));
        goal.setName(goalRequest.getName());
        goal.setDescription(goalRequest.getDescription());
        return goalRepository.save(goal);
    }

    @Override
    public Goal createGoal(Goal goal) {
        return goalRepository.save(goal);
    }

    @Override
    public Goal getGoalById(Long goalId) {
        return goalRepository.findById(goalId)
                        .orElseThrow(() -> new ResourceNotFoundException("Goal", "Id", goalId));
    }

}
