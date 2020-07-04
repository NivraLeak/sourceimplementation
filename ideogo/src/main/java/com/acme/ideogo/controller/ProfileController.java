package com.acme.ideogo.controller;


import com.acme.ideogo.model.Application;
import com.acme.ideogo.model.Profile;
import com.acme.ideogo.resource.ApplicationResource;
import com.acme.ideogo.resource.ProfileResource;
import com.acme.ideogo.resource.SaveApplicationResource;
import com.acme.ideogo.resource.SaveProfileResource;
import com.acme.ideogo.service.ProfileService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
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

@Tag(name = "profiles", description = "the Profiles API")
@RestController
@CrossOrigin(origins = "*", methods= {RequestMethod.GET,RequestMethod.POST,RequestMethod.DELETE,RequestMethod.PUT})

@RequestMapping("/api")
public class ProfileController {

    @Autowired
    private ModelMapper mapper;

    @Autowired
    private ProfileService profileService;

    @Operation(summary = "Get Profiles", description = "Get All Profiles by Pages", tags = { "profiles" })
    @GetMapping("/profiles")
    public Page<ProfileResource> getAllProfiles(
            @Parameter(description="Pageable Parameter")
                    Pageable pageable) {
        Page<Profile> profilesPage = profileService.getAllProfiles(pageable);
        List<ProfileResource> resources = profilesPage.getContent().stream().map(this::convertToResource).collect(Collectors.toList());
        return new PageImpl<>(resources, pageable, resources.size());
    }

    @Operation(summary = "Get Profile by Id", description = "Get a Profile by specifying Id", tags = { "profiles" })
    @GetMapping("/profiles/{id}")
    public ProfileResource getProfileById(
            @Parameter(description="Profile Id")
            @PathVariable(name = "id") Long profileId) {
        return convertToResource(profileService.getProfileById(profileId));
    }


    @GetMapping("/users/{userId}/profiles")
    public Page<ProfileResource> getAllProfilesByUserId(
            @PathVariable(name = "userId") Long userId,
            Pageable pageable) {
        Page<Profile> applicationPage = profileService.getAllProfilesByUserId(userId, pageable);
        List<ProfileResource> resources = applicationPage.getContent().stream().map(this::convertToResource).collect(Collectors.toList());
        return new PageImpl<>(resources, pageable, resources.size());
    }

    @PostMapping("/users/{userId}/profiles")
    public ProfileResource createProfile(@PathVariable(name = "userId") Long userId,
                                         @Valid @RequestBody SaveProfileResource resource) {
        return convertToResource(profileService.createProfile(userId, convertToEntity(resource)));
    }

    @PutMapping("/users/{userId}/profiles/{profilesid}")
    public ProfileResource updateProfile(@PathVariable(name = "userId") Long userId,
                                         @PathVariable(name = "profileId") Long applicationId,
                                         @Valid @RequestBody SaveProfileResource resource) {
        return convertToResource(profileService.updateProfile(userId, applicationId, convertToEntity(resource)));
    }

    @DeleteMapping("/users/{userId}/profiles/{profileid}")
    public ResponseEntity<?> deleteProfile(@PathVariable(name = "userId") Long userId,
                                           @PathVariable(name = "profileId") Long profileId) {
        return profileService.deleteProfile(userId, profileId);
    }

    @GetMapping("/tags/{tagId}/profiles")
    public Page<ProfileResource> getAllProfileByTagId(@PathVariable(name = "tagId") Long tagId, Pageable pageable) {
        Page<Profile> profilesPage = profileService.getAllProfilesByTagId(tagId, pageable);
        List<ProfileResource> resources = profilesPage.getContent().stream().map(this::convertToResource).collect(Collectors.toList());
        return new PageImpl<>(resources, pageable, resources.size());
    }

    @PostMapping("/profiles/{profileId}/tags/{tagId}")
    public ProfileResource assignProfileTag(@PathVariable(name = "profileId") Long profileId,
                                            @PathVariable(name = "tagId") Long tagId) {
        return convertToResource(profileService.assignProfileTag(profileId, tagId));
    }

    @DeleteMapping("/profiles/{profileId}/tags/{tagId}")
    public ProfileResource unassignProfileTag(@PathVariable(name = "profileId") Long profileId,
                                        @PathVariable(name = "tagId") Long tagId) {

        return convertToResource(profileService.unassignProfileTag(profileId, tagId));
    }

    private Profile convertToEntity(SaveProfileResource resource) {
        return mapper.map(resource, Profile.class);
    }

    private ProfileResource convertToResource(Profile entity) {
        return mapper.map(entity, ProfileResource.class);
    }

}
