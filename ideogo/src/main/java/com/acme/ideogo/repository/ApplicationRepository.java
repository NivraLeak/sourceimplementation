package com.acme.ideogo.repository;

import com.acme.ideogo.model.Application;
import com.acme.ideogo.model.Project;
import com.acme.ideogo.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface ApplicationRepository extends JpaRepository<Application,Long> {
    Page<Application> findByUserId(Long userId, Pageable pageable);
    Optional<Application> findByIdAndUserId(Long id, Long userId);

    //Page<Application> findByProjectId(Long projectId, Pageable pageable);
    //Optional<Application> findByIdAndProjectId(Long id, Long projectId);
}
