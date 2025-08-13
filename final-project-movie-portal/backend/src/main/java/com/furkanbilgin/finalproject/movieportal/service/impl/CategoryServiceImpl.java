package com.furkanbilgin.finalproject.movieportal.service.impl;

import com.furkanbilgin.finalproject.movieportal.dto.movie.CategoryDTO;
import com.furkanbilgin.finalproject.movieportal.model.movie.Category;
import com.furkanbilgin.finalproject.movieportal.repository.CategoryRepository;
import com.furkanbilgin.finalproject.movieportal.service.CategoryService;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {
  private final CategoryRepository categoryRepository;
  private final ModelMapper modelMapper;

  @Override
  public CategoryDTO saveCategory(CategoryDTO categoryDTO) {
    var saved = categoryRepository.save(modelMapper.map(categoryDTO, Category.class));
    return modelMapper.map(saved, CategoryDTO.class);
  }

  @Override
  public List<CategoryDTO> getAllCategories() {
    return categoryRepository.findAll().stream()
        .map(category -> modelMapper.map(category, CategoryDTO.class))
        .collect(Collectors.toList());
  }

  @Override
  public List<CategoryDTO> getAllCategories(Pageable pageable) {
    return categoryRepository.findAll(pageable).stream()
        .map(category -> modelMapper.map(category, CategoryDTO.class))
        .collect(Collectors.toList());
  }

  @Override
  public Optional<CategoryDTO> getCategoryById(Long id) {
    return categoryRepository
        .findById(id)
        .map(category -> modelMapper.map(category, CategoryDTO.class));
  }

  @Override
  public void deleteCategoryById(Long id) {
    categoryRepository.deleteById(id);
  }

  @Override
  public Optional<CategoryDTO> updateCategory(Long id, CategoryDTO categoryDTO) {
    return categoryRepository
        .findById(id)
        .map(
            existing -> {
              existing.setName(categoryDTO.getName());
              var updated = categoryRepository.save(existing);
              return modelMapper.map(updated, CategoryDTO.class);
            });
  }
}
