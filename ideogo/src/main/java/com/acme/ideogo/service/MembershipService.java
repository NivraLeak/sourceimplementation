package com.acme.ideogo.service;

import com.acme.ideogo.model.Membership;
import com.acme.ideogo.model.Profile;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

public interface MembershipService {
    ResponseEntity<?> deleteMembership(Long membershipId);
    Membership updateMembership(Long profileId, Membership membershipRequest);
    Membership createMembership(Membership membership);
    Membership getMembershipById(Long membershipId);
    Page<Membership> getAllMembership(Pageable pageable);
}
