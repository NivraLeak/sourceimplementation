package com.acme.ideogo.service;


import com.acme.ideogo.exception.ResourceNotFoundException;
import com.acme.ideogo.model.MTask;
import com.acme.ideogo.repository.MTaskRepository;
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
public class MTaskServiceImpl implements MTaskService {
    @Autowired
    private ProjectScheduleRepository projectScheduleRepository;

    @Autowired
    private MTaskRepository mTaskRepository;

    @Override
    public Page<MTask> getAllMTaskByProjectScheduleId(Long projectId, Pageable pageable) {
        return mTaskRepository.findByProjectScheduleId(projectId, pageable);
    }

    @Override
    public MTask getMTaskById(Long mTaskId) {
        return mTaskRepository.findById(mTaskId)
                .orElseThrow(() -> new ResourceNotFoundException("MTask", "Id", mTaskId));
    }

    @Override
    public MTask createMTask(MTask mTask) {
        return mTaskRepository.save(mTask);
    }

    @Override
    public MTask updateMTask(Long mTaskId, MTask mTaskRequest) {
        MTask mTask = mTaskRepository.findById(mTaskId)
                .orElseThrow(() -> new ResourceNotFoundException("MTask", "Id", mTaskId));
        mTask.setName(mTaskRequest.getName());
        return mTaskRepository.save(mTask);
    }

    @Override
    public ResponseEntity<?> deleteMTask(Long mTaskId) {
        MTask mTask = mTaskRepository.findById(mTaskId)
                .orElseThrow(() -> new ResourceNotFoundException("MTask", "Id", mTaskId));
        mTaskRepository.delete(mTask);
        return ResponseEntity.ok().build();
    }
}
