package com.acme.ideogo.service;


import com.acme.ideogo.exception.ResourceNotFoundException;
import com.acme.ideogo.model.Application;
import com.acme.ideogo.model.Category;
import com.acme.ideogo.model.Profile;
import com.acme.ideogo.model.Tag;
import com.acme.ideogo.repository.CategoryRepository;
import com.acme.ideogo.repository.ProfileRepository;
import com.acme.ideogo.repository.TagRepository;
import com.acme.ideogo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProfileServiceImpl implements ProfileService {

    @Autowired
    private TagRepository tagRepository;

    @Autowired
    private UserRepository userRepository;


    @Autowired
    private ProfileRepository profileRepository;

    @Override
    public Page<Profile> getAllProfilesByTagId(Long tagId, Pageable pageable) {
        return tagRepository.findById(tagId).map(tag -> {
            List<Profile> profiles = tag.getProfiles();
            int profilesCount = profiles.size();
            return new PageImpl<>(profiles, pageable, profilesCount);
        })
                .orElseThrow(() -> new ResourceNotFoundException("Tag", "Id", tagId));

    }

    @Override
    public ResponseEntity<?> deleteProfile(Long userId,Long profileId) {
        return profileRepository.findByIdAndUserId(profileId, userId).map(application -> {
            profileRepository.delete(application);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException(
                "Application not found with Id " + profileId + " and projectId " + userId));
    }

    @Override
    public Profile updateProfile(Long userId,Long postId, Profile profileRequest) {
        if(!userRepository.existsById(userId))
            throw new ResourceNotFoundException("User", "Id", userId);

        return profileRepository.findById(userId).map(application -> {
            application.setTypeuser(profileRequest.getTypeuser());
            application.setAge(profileRequest.getAge());
            application.setOccupation(profileRequest.getOccupation());
            application.setGender(profileRequest.getGender());
            return profileRepository.save(application);
        }).orElseThrow(() -> new ResourceNotFoundException("Profile", "Id", postId));

    }

    @Override
    public Profile createProfile(Long userId,Profile profile) {

        return userRepository.findById(userId).map(post -> {
            profile.setUser(post);
            return profileRepository.save(profile);
        }).orElseThrow(() -> new ResourceNotFoundException(
                "User", "Id", userId));
    }

    @Override
    public Profile getProfileById(Long profileId) {
        return profileRepository.findById(profileId)
                .orElseThrow(() -> new ResourceNotFoundException("Profile", "Id", profileId));
    }

    @Override
    public Page<Profile> getAllProfiles(Pageable pageable) {
        return profileRepository.findAll(pageable);
    }


    @Override
    public Profile assignProfileTag(Long profileId, Long tagId) {
        Tag tag = tagRepository.findById(tagId)
                .orElseThrow(() -> new ResourceNotFoundException("Tag", "Id", tagId));
        return profileRepository.findById(profileId).map(profile -> {
            if(!profile.getTags().contains(tag)) {
                profile.getTags().add(tag);
                return profileRepository.save(profile);
            }
            return profile;
        }).orElseThrow(() -> new ResourceNotFoundException("Profile", "Id", profileId));
    }

    @Override
    public Profile unassignProfileTag(Long profileId, Long tagId) {
        Tag tag = tagRepository.findById(tagId)
                .orElseThrow(() -> new ResourceNotFoundException("Tag", "Id", tagId));
        return profileRepository.findById(profileId).map(profile -> {
            profile.getTags().remove(tag);
            return profileRepository.save(profile);
        }).orElseThrow(() -> new ResourceNotFoundException("Profile", "Id", profileId));

    }

    @Override
    public Page<Profile> getAllProfilesByUserId(Long userId, Pageable pageable) {
        return profileRepository.findByUserId(userId, pageable);
    }
}
