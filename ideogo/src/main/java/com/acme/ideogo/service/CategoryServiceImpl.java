package com.acme.ideogo.service;

import com.acme.ideogo.exception.ResourceNotFoundException;
import com.acme.ideogo.model.Category;
import com.acme.ideogo.model.Tag;
import com.acme.ideogo.repository.CategoryRepository;
import com.acme.ideogo.repository.TagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private TagRepository tagRepository;
    @Autowired
    private CategoryRepository categoryRepository;
    @Override
    public Category assignCategoryTag(Long categoryId, Long tagId) {
        Tag tag = tagRepository.findById(tagId)
                .orElseThrow(() -> new ResourceNotFoundException("Tag", "Id", tagId));
        return categoryRepository.findById(categoryId).map(category -> {
            if(!category.getTags().contains(tag)) {
                category.getTags().add(tag);
                return categoryRepository.save(category);
            }
            return category;
        }).orElseThrow(() -> new ResourceNotFoundException("Category", "Id", categoryId));

    }

    @Override
    public Category unassignCategoryTag(Long categoryId, Long tagId) {
        Tag tag = tagRepository.findById(tagId)
                .orElseThrow(() -> new ResourceNotFoundException("Tag", "Id", tagId));
        return categoryRepository.findById(categoryId).map(category -> {
            category.getTags().remove(tag);
            return categoryRepository.save(category);
        }).orElseThrow(() -> new ResourceNotFoundException("Category", "Id", categoryId));
    }

    public Page<Category> getAllCategoriesByTagId(Long tagId, Pageable pageable) {
        return tagRepository.findById(tagId).map(tag -> {
            List<Category> posts = tag.getCategories();
            int categoryCount = posts.size();
            return new PageImpl<>(posts, pageable, categoryCount);
        })
                .orElseThrow(() -> new ResourceNotFoundException("Tag", "Id", tagId));
    }

    @Override
    public ResponseEntity<?> deleteCategory(Long categoryId) {
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new ResourceNotFoundException("Category", "Id", categoryId));
        categoryRepository.delete(category);
        return ResponseEntity.ok().build();
    }

    @Override
    public Category updateCategory(Long categoryId, Category categoryRequest) {
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new ResourceNotFoundException("Category", "Id", categoryId));
        category.setName(categoryRequest.getName());
        category.setDescription(categoryRequest.getDescription());
        return categoryRepository.save(category);
    }

    @Override
    public Category createCategory(Category category) {
        return categoryRepository.save(category);
    }

    @Override
    public Category getCategoryById(Long categoryId) {
        return categoryRepository.findById(categoryId)
                .orElseThrow(() -> new ResourceNotFoundException("Category", "Id", categoryId));
    }

    @Override
    public Page<Category> getAllCategories(Pageable pageable) {
        return categoryRepository.findAll(pageable);
    }
}
