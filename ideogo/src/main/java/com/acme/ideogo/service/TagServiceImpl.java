package com.acme.ideogo.service;

import com.acme.ideogo.exception.ResourceNotFoundException;
import com.acme.ideogo.model.Category;
import com.acme.ideogo.model.Project;
import com.acme.ideogo.model.Tag;
import com.acme.ideogo.repository.CategoryRepository;
import com.acme.ideogo.repository.ProjectRepository;
import com.acme.ideogo.repository.TagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TagServiceImpl implements TagService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private TagRepository tagRepository;

    public Page<Tag> getAllTagsByCategoryId(Long categoryId, Pageable pageable) {
        return categoryRepository.findById(categoryId).map(category -> {
            List<Tag> tags = category.getTags();
            int tagsCount = tags.size();
            return new PageImpl<>(tags, pageable, tagsCount);
        })
                .orElseThrow(() -> new ResourceNotFoundException("Category", "Id", categoryId));
    }

    @Override
    public ResponseEntity<?> deleteTag(Long tagId) {
        Tag tag = tagRepository.findById(tagId)
                .orElseThrow(() -> new ResourceNotFoundException("Tag", "Id", tagId));
        tagRepository.delete(tag);
        return ResponseEntity.ok().build();
    }

    @Override
    public Tag updateTag(Long tagId, Tag tagRequest) {
        Tag tag = tagRepository.findById(tagId)
                .orElseThrow(() -> new ResourceNotFoundException("Tag", "Id", tagId));
        tag.setName(tagRequest.getName());
        return tagRepository.save(tag);
    }

    @Override
    public Tag createTag(Tag tag) {
        return tagRepository.save(tag);
    }

    @Override
    public Tag getTagById(Long tagId) {
        return tagRepository.findById(tagId)
                .orElseThrow(() -> new ResourceNotFoundException("Tag", "Id", tagId));
    }

    @Override
    public Page<Tag> getAllTags(Pageable pageable) {
        return tagRepository.findAll(pageable);
    }
}
