package com.acme.ideogo.service;

import com.acme.ideogo.exception.ResourceNotFoundException;
import com.acme.ideogo.model.Resource;
import com.acme.ideogo.repository.ResourceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class ResourceServiceImpl implements ResourceService {

   @Autowired
   private ResourceRepository resourceRepository;



    @Override
    public Page<Resource> getAllResources(Pageable pageable) {
        return resourceRepository.findAll(pageable);
    }

    @Override
    public Resource getResourceById(Long resourceId) {
        return resourceRepository.findById(resourceId)
                .orElseThrow(() -> new ResourceNotFoundException("resource", "Id", resourceId));
    }

    @Override
    public Resource createResource(Resource resource) {
        return resourceRepository.save(resource);
    }

    @Override
    public Resource updateResource(Long resourceId, Resource resourceDetails) {
        return resourceRepository.findById(resourceId).map(resource -> {
            resource.setQuantity(resourceDetails.getQuantity());
            return resourceRepository.save(resource);
        }).orElseThrow(() -> new ResourceNotFoundException("Resource", "Id", resourceId));
    }

    @Override
    public ResponseEntity<?> deleteResource(Long resourceId) {
        return resourceRepository.findById(resourceId).map(tag -> {
            resourceRepository.delete(tag);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException("Resource", "Id", resourceId));
    }
}
