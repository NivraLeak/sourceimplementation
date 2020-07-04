package com.acme.ideogo.service;

import com.acme.ideogo.model.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.ResponseEntity;

public interface TagService {
    Page<Tag> getAllTags(Pageable pageable);
    Page<Tag> getAllTagsByCategoryId(Long postId, Pageable pageable);
    Tag getTagById(Long tagId);
    Tag createTag(Tag tag);
    Tag updateTag(Long tagId, Tag tagDetails);
    ResponseEntity<?> deleteTag(Long tagId);
}
