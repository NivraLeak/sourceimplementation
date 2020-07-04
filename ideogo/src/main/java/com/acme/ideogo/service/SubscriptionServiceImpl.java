package com.acme.ideogo.service;

import com.acme.ideogo.exception.ResourceNotFoundException;
import com.acme.ideogo.model.Membership;
import com.acme.ideogo.model.Subscription;
import com.acme.ideogo.repository.MembershipRepository;
import com.acme.ideogo.repository.SubscriptionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class SubscriptionServiceImpl implements SubscriptionService {
    @Autowired
    private MembershipRepository membershipRepository;

    @Autowired
    private SubscriptionRepository subscriptionRepository;


    @Override
    public Page<Subscription> getAllSubscriptionsByMembershipId(Long membershipId, Pageable pageable) {

        return subscriptionRepository.findByMembershipId(membershipId,pageable);
    }

    @Override
    public Subscription createSubscription(Long membershipId, Subscription subscription) {
        return membershipRepository.findById(membershipId).map(membership -> {
            subscription.setMembership(membership);
            return subscriptionRepository.save(subscription);
        }).orElseThrow(() -> new ResourceNotFoundException(
                "Tag", "Id", membershipId));
    }

    @Override
    public Subscription updateSubscription(Long membershipId, Long subscriptionId, Subscription subscriptionDetails) {
        if(!membershipRepository.existsById(membershipId))
            throw new ResourceNotFoundException("Tag", "Id", membershipId);

        return subscriptionRepository.findById(subscriptionId).map(subscription -> {
            subscription.setPrice(subscriptionDetails.getPrice());
            return subscriptionRepository.save(subscription);
        }).orElseThrow(() -> new ResourceNotFoundException("Skill", "Id", subscriptionId));
    }

    @Override
    public ResponseEntity<?> deleteSubscription(Long membershipId, Long subscriptionId) {
        return subscriptionRepository.findByIdAndMembershipId(subscriptionId, membershipId).map(subscription -> {
            subscriptionRepository.delete(subscription);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException(
                "Subscription not found with Id " + subscriptionId + " and MembershipId " + membershipId));
    }

    @Override
    public Subscription getSubscriptionByIdAndMembershipId(Long membershipId, Long subscriptionId) {
        return subscriptionRepository.findByIdAndMembershipId(membershipId,subscriptionId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Subscription not found with Id " + subscriptionId +
                                " and MembershipId " + membershipId ));
    }


}
