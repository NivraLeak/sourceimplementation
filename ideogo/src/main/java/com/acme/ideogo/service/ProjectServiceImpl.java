package com.acme.ideogo.service;


import com.acme.ideogo.exception.ResourceNotFoundException;
import com.acme.ideogo.model.*;
import com.acme.ideogo.repository.CategoryRepository;
import com.acme.ideogo.repository.ProjectRepository;
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
public class ProjectServiceImpl implements ProjectService {

    @Autowired
    private ProjectRepository projectRepository;


    @Autowired
    private UserRepository userRepository;


    @Autowired
    private TagRepository tagRepository;

    public Page<Project> getAllProjectsByTagId(Long tagId, Pageable pageable) {
        return tagRepository.findById(tagId).map(tag -> {
            List<Project> projects = tag.getProjects();
            int tagsCount = projects.size();
            return new PageImpl<>(projects, pageable, tagsCount);
        })
                .orElseThrow(() -> new ResourceNotFoundException("Project", "Id", tagId));
    }

    @Override
    public ResponseEntity<?> deleteProject(Long userId,Long projectId) {
        return projectRepository.findByIdAndLeaderId(projectId, userId).map(application -> {
            projectRepository.delete(application);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException(
                "Application not found with Id " + projectId + " and projectId " + userId));
    }

    @Override
    public Project updateProject(Long userId,Long projectId, Project projectRequest) {
        if(!userRepository.existsById(userId))
            throw new ResourceNotFoundException("User", "Id", userId);

        return projectRepository.findById(userId).map(project -> {
        project.setName(projectRequest.getName());
        project.setDescription(projectRequest.getDescription());
        project.setContent(projectRequest.getContent());
        return projectRepository.save(project);
        }).orElseThrow(() -> new ResourceNotFoundException("Project", "Id", projectId));
    }

    @Override
    public Project createProject(Long userId,Project project) {
        return userRepository.findById(userId).map(post -> {
            project.setLeader(post);
            return projectRepository.save(project);
        }).orElseThrow(() -> new ResourceNotFoundException(
                "User", "Id", userId));
    }

    @Override
    public Project getProjectById(Long proyectId) {
        return projectRepository.findById(proyectId)
                .orElseThrow(() -> new ResourceNotFoundException("Project", "Id", proyectId));
    }

    @Override
    public Page<Project> getAllProjects(Pageable pageable) {
        return projectRepository.findAll(pageable);
    }

    @Override
    public Project assignProjectUser(Long projectId, Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User", "Id", userId));
        return projectRepository.findById(projectId).map(profile -> {
            if(!profile.getUsers().contains(user)) {
                profile.getUsers().add(user);
                return projectRepository.save(profile);
            }
            return profile;
        }).orElseThrow(() -> new ResourceNotFoundException("Project", "Id", projectId));
    }

    @Override
    public Project unassignProjectUser(Long projectId, Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User", "Id", userId));
        return projectRepository.findById(projectId).map(profile -> {
            profile.getUsers().remove(user);
            return projectRepository.save(profile);
        }).orElseThrow(() -> new ResourceNotFoundException("Project", "Id", projectId));
    }

    @Override
    public Page<Project> getAllProjectsByUserId(Long userId, Pageable pageable) {
        return projectRepository.findByLeaderId(userId, pageable);
    }
}
