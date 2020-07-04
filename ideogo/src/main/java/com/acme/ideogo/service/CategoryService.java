package com.acme.ideogo.service;

import com.acme.ideogo.model.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.ResponseEntity;

public interface CategoryService {
    Category assignCategoryTag(Long categoryId, Long tagId);
    Category  unassignCategoryTag(Long categoryId, Long tagId);
    Page<Category > getAllCategoriesByTagId(Long tagId, Pageable pageable);
    ResponseEntity<?> deleteCategory(Long postId);
    Category  updateCategory(Long postId, Category  postRequest);
    Category  createCategory(Category  category);
    Category  getCategoryById(Long postId);
    Page<Category> getAllCategories(Pageable pageable);
}
