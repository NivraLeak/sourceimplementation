package com.acme.ideogo.service;

import com.acme.ideogo.exception.ResourceNotFoundException;
import com.acme.ideogo.model.Membership;
import com.acme.ideogo.model.Tag;
import com.acme.ideogo.repository.MembershipRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class MembershipServiceImpl implements MembershipService {

    @Autowired
    private MembershipRepository membershipRepository;

    @Override
    public ResponseEntity<?> deleteMembership(Long membershipId) {
        Membership membership = membershipRepository.findById(membershipId)
                .orElseThrow(() -> new ResourceNotFoundException("Tag", "Id", membershipId));
        membershipRepository.delete(membership);
        return ResponseEntity.ok().build();
    }

    @Override
    public Membership updateMembership(Long membershipId, Membership membershipRequest) {
        Membership membership = membershipRepository.findById(membershipId)
                .orElseThrow(() -> new ResourceNotFoundException("Tag", "Id", membershipId));
        membership.setName(membershipRequest.getName());
        return membershipRepository.save(membership);
    }

    @Override
    public Membership createMembership(Membership membership) {
        return membershipRepository.save(membership);
    }

    @Override
    public Membership getMembershipById(Long membershipId) {
        return membershipRepository.findById(membershipId)
                .orElseThrow(() -> new ResourceNotFoundException("MembershipId", "Id", membershipId));
    }

    @Override
    public Page<Membership> getAllMembership(Pageable pageable) {
        return membershipRepository.findAll(pageable);
    }
}
