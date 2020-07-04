package com.acme.ideogo.service;

import com.acme.ideogo.exception.ResourceNotFoundException;
import com.acme.ideogo.model.Activity;
import com.acme.ideogo.model.ProjectSchedule;
import com.acme.ideogo.repository.ActivityRepository;
import com.acme.ideogo.repository.ProjectRepository;
import com.acme.ideogo.repository.ProjectScheduleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ActivityServiceImpl implements ActivityService{
    @Autowired
    private ProjectScheduleRepository projectScheduleRRepository;

    @Autowired
    private ActivityRepository activityRepository;

    @Override
    public Page<Activity> getAllActivitiesByProjectScheduleId(Long activityId, Pageable pageable) {
        return activityRepository.findByProjectScheduleId(activityId, pageable);
    }

    @Override
    public Activity getActivityById(Long activityId) {
        return activityRepository.findById(activityId)
                .orElseThrow(() -> new ResourceNotFoundException("Activity", "Id", activityId));
    }

    @Override
    public Activity createActivity(Activity activity) {
        return activityRepository.save(activity);
    }

    @Override
    public Activity updateActivity(Long activityId, Activity activityRequest) {
        Activity activity = activityRepository.findById(activityId)
                .orElseThrow(() -> new ResourceNotFoundException("MTask", "Id", activityId));
        activity.setName(activityRequest.getName());
        return activityRepository.save(activity);
    }

    @Override
    public ResponseEntity<?> deleteActivity(Long activityId) {
        Activity activity = activityRepository.findById(activityId)
                .orElseThrow(() -> new ResourceNotFoundException("Activity", "Id", activityId));
        activityRepository.delete(activity);
        return ResponseEntity.ok().build();
    }
}
