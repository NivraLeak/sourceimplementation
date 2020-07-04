package com.acme.ideogo.repository;

import com.acme.ideogo.model.Skill;
import com.acme.ideogo.model.Subscription;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SubscriptionRepository extends JpaRepository<Subscription, Long> {
    Page<Subscription> findByMembershipId(Long subscriptionId, Pageable pageable);
    Optional<Subscription> findByIdAndMembershipId(Long id, Long subscriptionId);
}
