package com.acme.ideogo.service;

import com.acme.ideogo.model.Activity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

public interface ActivityService {
    Page<Activity> getAllActivitiesByProjectScheduleId(Long activityId, Pageable pageable);
    Activity getActivityById(Long activityId);
    Activity createActivity(Activity activity);
    Activity updateActivity(Long activityId, Activity activityRequest);
    ResponseEntity<?> deleteActivity(Long activityId);
}
