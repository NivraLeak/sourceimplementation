package com.acme.ideogo.service;

import com.acme.ideogo.model.Application;
import com.acme.ideogo.model.Profile;
import com.acme.ideogo.model.Project;
import com.acme.ideogo.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

public interface ApplicationService {
    Page<Application> getAllApplicationsByUserId(Long userId, Pageable pageable);
    //Application getApplicationByIdAndUserId(Long userId, Long applicationId);
    Application createApplication(Long userId, Application application);
    Application updateApplication(Long userId, Long applicationId, Application applicationDetails);
    ResponseEntity<?> deleteApplication(Long userId, Long applicationId);
    //Page<Application> getAllApplicationsByProjectId(Long projectId, Pageable pageable);
    //Application getApplicationByIdAndProjectId(Long projectId, Long applicationId);


    //ResponseEntity<?> deleteApplication(Long projectId, Long applicationId);

     //return userRepository.findById(userId).map(user -> {
      //  application.setUser(user);
      //  return applicationRepository.save(application);
    //}).orElseThrow(() -> new ResourceNotFoundException(
      //          "User", "Id", userId));
}
