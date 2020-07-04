package com.acme.ideogo.repository;

import com.acme.ideogo.model.Activity;
import com.acme.ideogo.model.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ActivityRepository extends JpaRepository<Activity,Long>{
    Page<Activity> findByProjectScheduleId(Long activityId, Pageable pageable);

}
