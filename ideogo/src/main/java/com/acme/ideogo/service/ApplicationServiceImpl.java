package com.acme.ideogo.service;

import com.acme.ideogo.exception.ResourceNotFoundException;
import com.acme.ideogo.model.Application;
import com.acme.ideogo.model.Category;
import com.acme.ideogo.model.Project;
import com.acme.ideogo.model.User;
import com.acme.ideogo.repository.ApplicationRepository;
import com.acme.ideogo.repository.ProjectRepository;
import com.acme.ideogo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ApplicationServiceImpl implements ApplicationService {

    //@Autowired
    //private ProjectRepository projectRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ApplicationRepository applicationRepository;


    @Override
    public  Page<Application> getAllApplicationsByUserId(Long userId, Pageable pageable) {
        return applicationRepository.findByUserId(userId, pageable);
    }


    @Override
    public Application createApplication(Long userId , Application application) {
       return userRepository.findById(userId).map(user -> {
            application.setUser(user);





            return applicationRepository.save(application);
        }).orElseThrow(() -> new ResourceNotFoundException(
               "Application", "Id", userId));
    }

    @Override
    public Application updateApplication(Long userId, Long applicationId, Application applicationDetails) {
        if(!userRepository.existsById(userId))
            throw new ResourceNotFoundException("User", "Id", userId);

        return applicationRepository.findById(userId).map(application -> {
            application.setState(applicationDetails.getState());
            application.setOrderRequest(applicationDetails.getOrderRequest());
            return applicationRepository.save(application);
        }).orElseThrow(() -> new ResourceNotFoundException("Application", "Id", applicationId));

    }

    @Override
    public ResponseEntity<?> deleteApplication(Long userId, Long applicationId) {
        return applicationRepository.findByIdAndUserId(applicationId, userId).map(application -> {
            applicationRepository.delete(application);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException(
                "Application not found with Id " + applicationId + " and projectId " + userId));
    }

   //@Override
   //public Page<Application> getAllApplicationsByProjectId(Long projectId, Pageable pageable) {
   //    return applicationRepository.findByProjectId(projectId, pageable);
   //}

    //@Override
    //public Application getApplicationByIdAndProjectId(Long projectId, Long applicationId) {
    //    return applicationRepository.findByIdAndProjectId(applicationId, projectId)
    //            .orElseThrow(() -> new ResourceNotFoundException(
    //                    "Application not found with Id " + applicationId +
    //                            " and projectId" + projectId));
    //}




}
