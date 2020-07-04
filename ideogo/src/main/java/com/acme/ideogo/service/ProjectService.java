package com.acme.ideogo.service;

import com.acme.ideogo.model.Profile;
import com.acme.ideogo.model.Project;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.ResponseEntity;

public interface ProjectService  {
    Page<Project> getAllProjectsByTagId(Long tagId, Pageable pageable);
    ResponseEntity<?> deleteProject(Long userId,Long postId);
    Project updateProject(Long userId,Long postId, Project postRequest);
    Project createProject(Long userId,Project project);
    Project getProjectById(Long postId);
    Page<Project> getAllProjects(Pageable pageable);


    Project assignProjectUser(Long projectId, Long userId);
    Project unassignProjectUser(Long projectId, Long userId);

    Page<Project> getAllProjectsByUserId(Long userId, Pageable pageable);
}
