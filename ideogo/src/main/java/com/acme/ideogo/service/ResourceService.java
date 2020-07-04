package com.acme.ideogo.service;

import com.acme.ideogo.model.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

public interface ResourceService {
    Page<Resource> getAllResources(Pageable pageable);
    Resource getResourceById(Long resourceId);
    Resource createResource(Resource resource);
    Resource updateResource(Long resourceId, Resource resourceDetails);
    ResponseEntity<?> deleteResource(Long resourceId);
}
