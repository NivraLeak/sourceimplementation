package com.acme.ideogo.controller;


import com.acme.ideogo.model.Category;
import com.acme.ideogo.model.Tag;
import com.acme.ideogo.resource.TagResource;;
import com.acme.ideogo.resource.SaveTagResource;
import com.acme.ideogo.service.ProjectService;
import com.acme.ideogo.service.TagService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;

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

@io.swagger.v3.oas.annotations.tags.Tag(name = "tags", description = "the Tags API")
@RestController
@CrossOrigin(origins = "*", methods= {RequestMethod.GET,RequestMethod.POST,RequestMethod.DELETE,RequestMethod.PUT})

@RequestMapping("/api")
public class TagController {

    @Autowired
    private ModelMapper mapper;

    @Autowired
    private TagService tagService;

    @Operation(summary = "Get Tags", description = "Get All Tags by Pages", tags = { "tags" })
    @GetMapping("/tags")
    public Page<TagResource> getAllTags(
            @Parameter(description="Pageable Parameter")
                    Pageable pageable) {
        Page<Tag> tagPage = tagService.getAllTags(pageable);
        List<TagResource> resources = tagPage.getContent().stream().map(this::convertToResource).collect(Collectors.toList());
        return new PageImpl<>(resources, pageable, resources.size());
    }

    @Operation(summary = "Get Tag by Id", description = "Get a Tag by specifying Id", tags = { "tags" })
    @GetMapping("/tags/{id}")
    public TagResource getTagById(
            @Parameter(description="Project Id")
            @PathVariable(name = "id") Long tagId) {
        return convertToResource(tagService.getTagById(tagId));
    }

    @PostMapping("/tags")
    public TagResource createTag(@Valid @RequestBody SaveTagResource resource)  {
        Tag tag = convertToEntity(resource);
        return convertToResource(tagService.createTag(tag));
    }

    @PutMapping("/tags/{id}")
    public TagResource updateTag(@PathVariable(name = "id") Long projectId, @Valid @RequestBody SaveTagResource resource) {
        Tag tag = convertToEntity(resource);
        return convertToResource(tagService.updateTag(projectId, tag));
    }

    @DeleteMapping("/tags/{id}")
    public ResponseEntity<?> deleteTag(@PathVariable(name = "id") Long tagId) {
        return tagService.deleteTag(tagId);
    }

    @GetMapping("/categories/{categoriesId}/tags")
    public Page<TagResource> getAllTagsByCategoryId(@PathVariable(name = "tagId") Long tagId, Pageable pageable) {
        Page<Tag> tagsPage = tagService.getAllTagsByCategoryId(tagId, pageable);
        List<TagResource> resources = tagsPage.getContent().stream().map(this::convertToResource).collect(Collectors.toList());
        return new PageImpl<>(resources, pageable, resources.size());
    }

    private Tag convertToEntity(SaveTagResource resource) {
        return mapper.map(resource, Tag.class);
    }

    private TagResource convertToResource(Tag entity) {
        return mapper.map(entity, TagResource.class);
    }

}

