package com.furkanbilgin.finalproject.movieportal.service;

import com.furkanbilgin.finalproject.movieportal.dto.movie.CategoryDTO;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Pageable;

public interface CategoryService {
  CategoryDTO saveCategory(CategoryDTO categoryDTO);

  List<CategoryDTO> getAllCategories();

  List<CategoryDTO> getAllCategories(Pageable pageable);

  Optional<CategoryDTO> getCategoryById(Long id);

  void deleteCategoryById(Long id);

  Optional<CategoryDTO> updateCategory(Long id, CategoryDTO categoryDTO);
}
