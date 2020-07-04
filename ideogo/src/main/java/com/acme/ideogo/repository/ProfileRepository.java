package com.acme.ideogo.repository;

import com.acme.ideogo.model.Application;
import com.acme.ideogo.model.Profile;
import com.acme.ideogo.model.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProfileRepository extends JpaRepository<Profile, Long> {
    Page<Profile> findByUserId(Long userId, Pageable pageable);
    Optional<Profile> findByIdAndUserId(Long id, Long userId);
}
