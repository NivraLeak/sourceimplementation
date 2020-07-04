package com.acme.ideogo.controller;

import com.acme.ideogo.model.Category;
import com.acme.ideogo.resource.CategoryResource;
import com.acme.ideogo.resource.SaveCategoryResource;
import com.acme.ideogo.service.CategoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
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

@Tag(name = "categories", description = "the Categories API")
@RestController
@CrossOrigin(origins = "*", methods= {RequestMethod.GET,RequestMethod.POST,RequestMethod.DELETE,RequestMethod.PUT})

@RequestMapping("/api")
public class CategoryController {

    @Autowired
    private ModelMapper mapper;

    @Autowired
    private CategoryService categoryService;

    @Operation(summary = "Get Categories", description = "Get All Categories by Pages", tags = { "categories" })
    @GetMapping("/posts")
    public Page<CategoryResource> getAllCategories(
            @Parameter(description="Pageable Parameter")
                    Pageable pageable) {
        Page<Category> categoriesPage = categoryService.getAllCategories(pageable);
        List<CategoryResource> resources = categoriesPage.getContent().stream().map(this::convertToResource).collect(Collectors.toList());
        return new PageImpl<>(resources, pageable, resources.size());
    }

    @Operation(summary = "Get Category by Id", description = "Get a Category by specifying Id", tags = { "categories" })
    @GetMapping("/categories/{id}")
    public CategoryResource getCategoryById(
            @Parameter(description="Category Id")
            @PathVariable(name = "id") Long categoryId) {
        return convertToResource(categoryService.getCategoryById(categoryId));
    }

    @PostMapping("/categories")
    public CategoryResource createCategory(@Valid @RequestBody SaveCategoryResource resource)  {
        Category categories = convertToEntity(resource);
        return convertToResource(categoryService.createCategory(categories));
    }

    @PutMapping("/categories/{id}")
    public CategoryResource updateCategory(@PathVariable(name = "id") Long categoryId, @Valid @RequestBody SaveCategoryResource resource) {
        Category category = convertToEntity(resource);
        return convertToResource(categoryService.updateCategory(categoryId, category));
    }

    @DeleteMapping("/categories/{id}")
    public ResponseEntity<?> deleteCategory(@PathVariable(name = "id") Long categoryId) {
        return categoryService.deleteCategory(categoryId);
    }

    @GetMapping("/tags/{tagId}/categories")
    public Page<CategoryResource> getAllCategoriesByTagId(@PathVariable(name = "categoryId") Long categoryId, Pageable pageable) {
        Page<Category> categoriesPage = categoryService.getAllCategoriesByTagId(categoryId, pageable);
        List<CategoryResource> resources = categoriesPage.getContent().stream().map(this::convertToResource).collect(Collectors.toList());
        return new PageImpl<>(resources, pageable, resources.size());
    }

    @PostMapping("/categories/{categoryId}/tags/{tagId}")
    public CategoryResource assignCategoryTag(@PathVariable(name = "categoryId") Long categoryId,
                                      @PathVariable(name = "tagId") Long tagId) {
        return convertToResource(categoryService.assignCategoryTag(categoryId, tagId));
    }

    @DeleteMapping("/categories/{categoryId}/tags/{tagId}")
    public CategoryResource unassignPostTag(@PathVariable(name = "categoryId") Long categoryId,
                                        @PathVariable(name = "tagId") Long tagId) {

        return convertToResource(categoryService.unassignCategoryTag(categoryId, tagId));
    }

    private Category convertToEntity(SaveCategoryResource resource) {
        return mapper.map(resource, Category.class);
    }

    private CategoryResource convertToResource(Category entity) {
        return mapper.map(entity, CategoryResource.class);
    }

}
