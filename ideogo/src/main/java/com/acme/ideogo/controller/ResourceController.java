package com.acme.ideogo.controller;

import com.acme.ideogo.model.Resource;
import com.acme.ideogo.resource.ResourceResource;
import com.acme.ideogo.resource.SaveResourceResource;
import com.acme.ideogo.service.ResourceService;
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

@Tag(name = "resources", description = "the Resources API")
@RestController
@CrossOrigin(origins = "*", methods= {RequestMethod.GET,RequestMethod.POST,RequestMethod.DELETE,RequestMethod.PUT})

@RequestMapping("/api")
public class ResourceController {

    @Autowired
    private ModelMapper mapper;
    @Autowired
    private ResourceService resourceService;

    @GetMapping("/resources")
    public Page<ResourceResource> getAllTags(Pageable pageable) {
        List<ResourceResource> tags = resourceService.getAllResources(pageable).getContent().stream().map(this::convertToResource).collect(Collectors.toList());
        int tagsCount = tags.size();
        return new PageImpl<>(tags, pageable, tagsCount);
    }


    @GetMapping("/resources/{id}")
    public ResourceResource getTagById(@PathVariable(name = "id") Long tagId) {
        return convertToResource(resourceService.getResourceById(tagId));
    }

    @PostMapping("/resources")
    public ResourceResource createTag(@Valid @RequestBody SaveResourceResource resource) {
        return convertToResource(resourceService.createResource(convertToEntity(resource)));
    }
    @PutMapping("/resources/{id}")
    public ResourceResource updateTag(@PathVariable(name = "id") Long tagId, @Valid @RequestBody SaveResourceResource resource) {
        return convertToResource(resourceService.updateResource(tagId, convertToEntity(resource)));
    }

    @DeleteMapping("/resources/{id}")
    public ResponseEntity<?> deleteTag(@PathVariable(name = "id") Long tagId) {
        return resourceService.deleteResource(tagId);
    }

    private Resource convertToEntity(SaveResourceResource resource) {
        return mapper.map(resource, Resource.class);
    }

    private ResourceResource convertToResource(Resource entity) {
        return mapper.map(entity, ResourceResource.class);
    }

}
