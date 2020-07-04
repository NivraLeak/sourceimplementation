package com.acme.ideogo.repository;

import com.acme.ideogo.model.Project;
import com.acme.ideogo.model.Requirement;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.xml.stream.events.Comment;
import java.util.Optional;

@Repository
public interface RequirementRepository extends JpaRepository<Requirement,Long> {
    Page<Requirement> findByProjectId(Long requirementId, Pageable pageable);  /// quiero de 100 en 100, los organizo en paginmas de 100
    Optional<Requirement> findByIdAndProjectId(Long id, Long requirementId);
}
