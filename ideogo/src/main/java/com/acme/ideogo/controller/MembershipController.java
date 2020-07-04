package com.acme.ideogo.controller;

import com.acme.ideogo.model.Category;
import com.acme.ideogo.model.Membership;
import com.acme.ideogo.model.Tag;
import com.acme.ideogo.resource.*;
import com.acme.ideogo.service.MembershipService;
import com.acme.ideogo.service.TagService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@io.swagger.v3.oas.annotations.tags.Tag(name = "memberships", description = "the Membership API")
@RestController
@CrossOrigin(origins = "*", methods= {RequestMethod.GET,RequestMethod.POST,RequestMethod.DELETE,RequestMethod.PUT})

@RequestMapping("/api")
public class MembershipController {
    @Autowired
    private ModelMapper mapper;

    @Autowired
    private MembershipService membershipService;

    @Operation(summary = "Get Memberships", description = "Get All Membership by Pages", tags = { "memberships" })
    @GetMapping("/memberships")
    public Page<MembershipResource> getAllMemberships(
            @Parameter(description="Pageable Parameter")
                    Pageable pageable) {
        Page<Membership> tagPage = membershipService.getAllMembership(pageable);
        List<MembershipResource> resources = tagPage.getContent().stream().map(this::convertToResource).collect(Collectors.toList());
        return new PageImpl<>(resources, pageable, resources.size());
    }

    @Operation(summary = "Get Membership by Id", description = "Get a Membership by specifying Id", tags = { "memberships" })
    @GetMapping("/memberships/{id}")
    public MembershipResource getMembershipById(
            @Parameter(description="Membership Id")
            @PathVariable(name = "id") Long membershipId) {
        return convertToResource(membershipService.getMembershipById(membershipId));
    }

    @PostMapping("/memberships")
    public MembershipResource createTag(@Valid @RequestBody SaveMembershipResource resource)  {
        Membership membership = convertToEntity(resource);
        return convertToResource(membershipService.createMembership(membership));
    }

    @PutMapping("/memberships/{id}")
    public MembershipResource updateCategory(@PathVariable(name = "id") Long membershipId, @Valid @RequestBody SaveMembershipResource resource) {
        Membership membership = convertToEntity(resource);
        return convertToResource(membershipService.updateMembership(membershipId, membership));
    }


    @DeleteMapping("/memberships/{id}")
    public ResponseEntity<?> deleteTag(@PathVariable(name = "id") Long membershipId) {
        return membershipService.deleteMembership(membershipId);
    }


    private Membership convertToEntity(SaveMembershipResource resource) {
        return mapper.map(resource, Membership.class);
    }

    private MembershipResource convertToResource(Membership entity) {
        return mapper.map(entity, MembershipResource.class);
    }
}
