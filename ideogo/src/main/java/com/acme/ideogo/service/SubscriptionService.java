package com.acme.ideogo.service;

import com.acme.ideogo.model.Appointment;
import com.acme.ideogo.model.Membership;
import com.acme.ideogo.model.Skill;
import com.acme.ideogo.model.Subscription;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

public interface SubscriptionService {
    Page<Subscription> getAllSubscriptionsByMembershipId(Long membershipId, Pageable pageable);
    Subscription createSubscription(Long membershipId, Subscription subscription);
    Subscription updateSubscription(Long membershipId, Long subscriptionId, Subscription subscriptionDetails);
    ResponseEntity<?> deleteSubscription(Long membershipId, Long subscriptionId);
    Subscription getSubscriptionByIdAndMembershipId(Long membershipId, Long subscriptionId);
}
