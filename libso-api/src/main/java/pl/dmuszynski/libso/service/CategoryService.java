package pl.dmuszynski.libso.service;

import pl.dmuszynski.libso.payload.CategoryView;
import pl.dmuszynski.libso.payload.ProductView;
import pl.dmuszynski.libso.payload.dto.CategoryDTO;

import java.util.Set;

public interface CategoryService {
    CategoryDTO createCategory(CategoryDTO categoryDetails);
    CategoryDTO updateCategoryById(CategoryDTO categoryDetails, Long categoryId);
    Set<CategoryView> findAllCategoryDto();
    void deleteCategoryById(Long categoryId);
}
