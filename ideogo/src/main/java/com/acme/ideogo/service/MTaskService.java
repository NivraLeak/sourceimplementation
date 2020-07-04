package com.acme.ideogo.service;

import com.acme.ideogo.model.MTask;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

public interface MTaskService {
    Page<MTask> getAllMTaskByProjectScheduleId(Long projectId, Pageable pageable);
    MTask getMTaskById(Long mTaskId);
    MTask createMTask(MTask mTask);
    MTask updateMTask(Long mTaskId, MTask mTaskRequest);
    ResponseEntity<?> deleteMTask(Long mTaskId);
}
