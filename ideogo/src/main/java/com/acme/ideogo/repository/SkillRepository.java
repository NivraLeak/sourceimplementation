package com.acme.ideogo.repository;

import com.acme.ideogo.model.Skill;
import com.acme.ideogo.model.Tag;
import com.acme.ideogo.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SkillRepository  extends JpaRepository<Skill, Long> {
    Page<Skill> findByTagId(Long tagId, Pageable pageable);
    Optional<Skill> findByIdAndTagId(Long id, Long tagId);
}
