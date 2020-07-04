package com.acme.ideogo.repository;

import com.acme.ideogo.model.Activity;
import com.acme.ideogo.model.MTask;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MTaskRepository extends JpaRepository<MTask,Long> {
    Page<MTask> findByProjectScheduleId(Long mTaskId, Pageable pageable);
}
