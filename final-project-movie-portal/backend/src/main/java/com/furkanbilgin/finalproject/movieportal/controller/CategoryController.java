package com.furkanbilgin.finalproject.movieportal.controller;

import com.furkanbilgin.finalproject.movieportal.dto.movie.CategoryDTO;
import com.furkanbilgin.finalproject.movieportal.service.CategoryService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/categories")
@RequiredArgsConstructor
public class CategoryController {
  private final CategoryService categoryService;

  @PostMapping
  @PreAuthorize("hasAuthority('ADMIN')")
  public ResponseEntity<CategoryDTO> createCategory(@RequestBody CategoryDTO categoryDTO) {
    CategoryDTO savedCategory = categoryService.saveCategory(categoryDTO);
    return new ResponseEntity<>(savedCategory, HttpStatus.CREATED);
  }

  @GetMapping
  public ResponseEntity<List<CategoryDTO>> getAllCategories(Pageable pageable) {
    var categories = categoryService.getAllCategories(pageable);
    return ResponseEntity.ok(categories);
  }

  @GetMapping("/{id}")
  public ResponseEntity<CategoryDTO> getCategoryById(@PathVariable Long id) {
    return categoryService
        .getCategoryById(id)
        .map(ResponseEntity::ok)
        .orElse(ResponseEntity.notFound().build());
  }

  @PutMapping("/{id}")
  @PreAuthorize("hasAuthority('ADMIN')")
  public ResponseEntity<CategoryDTO> updateCategory(
      @PathVariable Long id, @RequestBody CategoryDTO categoryDTO) {
    return categoryService
        .updateCategory(id, categoryDTO)
        .map(ResponseEntity::ok)
        .orElse(ResponseEntity.notFound().build());
  }

  @DeleteMapping("/{id}")
  @PreAuthorize("hasAuthority('ADMIN')")
  public void deleteCategory(@PathVariable Long id) {
    categoryService.deleteCategoryById(id);
  }
}
