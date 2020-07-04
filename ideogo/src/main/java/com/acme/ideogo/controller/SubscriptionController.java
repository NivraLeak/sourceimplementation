package com.acme.ideogo.controller;

import com.acme.ideogo.model.Skill;
import com.acme.ideogo.model.Subscription;
import com.acme.ideogo.resource.SaveSkillResource;
import com.acme.ideogo.resource.SaveSubscriptionResource;
import com.acme.ideogo.resource.SkillResource;
import com.acme.ideogo.resource.SubscriptionResource;
import com.acme.ideogo.service.SkillService;
import com.acme.ideogo.service.SubscriptionService;
import io.swagger.v3.oas.annotations.tags.Tag;
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

@Tag(name = "subscriptions", description = "the Subscriptions API")
@RestController
@CrossOrigin(origins = "*", methods= {RequestMethod.GET,RequestMethod.POST,RequestMethod.DELETE,RequestMethod.PUT})

@RequestMapping("/api")
public class SubscriptionController {

    @Autowired
    private ModelMapper mapper;
    @Autowired
    private SubscriptionService subscriptionService;

    @GetMapping("/memberships/{membershipId}/subscriptions")
    public Page<SubscriptionResource> getAllSubscriptionsByTagId(
            @PathVariable(name = "membershipId") Long membershipsId,
            Pageable pageable) {
        Page<Subscription> skillPage = subscriptionService.getAllSubscriptionsByMembershipId(membershipsId, pageable);
        List<SubscriptionResource> resources = skillPage.getContent().stream().map(this::convertToResource).collect(Collectors.toList());
        return new PageImpl<>(resources, pageable, resources.size());
    }

    @GetMapping("/memberships/{membershipId}/subscriptions/{subscriptionId}")
    public SubscriptionResource getSubscriptionByIdAndMembershipId(@PathVariable(name = "membershipId") Long membershipId,
                                              @PathVariable(name = "subscriptionId") Long subscriptionId) {
        return convertToResource(subscriptionService.getSubscriptionByIdAndMembershipId(membershipId, subscriptionId));
    }

    @PostMapping("/membershipss/{membershipId}/subscriptions")
    public SubscriptionResource createSkill(@PathVariable(name = "membershipsId") Long membershipId,
                                     @Valid @RequestBody SaveSubscriptionResource resource) {
        return convertToResource(subscriptionService.createSubscription(membershipId, convertToEntity(resource)));

    }

    @PutMapping("/memberships/{membershipId}/subscriptions/{subscriptionId}")
    public SubscriptionResource updateSkill(@PathVariable(name = "membershipId") Long membershipId,
                                     @PathVariable(name = "subscriptionId") Long subscriptionId,
                                     @Valid @RequestBody SaveSubscriptionResource resource) {
        return convertToResource(subscriptionService.updateSubscription(membershipId, subscriptionId, convertToEntity(resource)));
    }

    @DeleteMapping("/memberships/{membershipId}/subscriptions/{subscriptionId}")
    public ResponseEntity<?> deleteSkill(@PathVariable(name = "membershipId") Long membershipId,
                                         @PathVariable(name = "subscriptionId") Long subscriptionId) {
        return subscriptionService.deleteSubscription(membershipId, subscriptionId);
    }

    private Subscription convertToEntity(SaveSubscriptionResource resource) {
        return mapper.map(resource, Subscription.class);
    }

    private SubscriptionResource convertToResource(Subscription entity) {
        return mapper.map(entity, SubscriptionResource.class);
    }




}
