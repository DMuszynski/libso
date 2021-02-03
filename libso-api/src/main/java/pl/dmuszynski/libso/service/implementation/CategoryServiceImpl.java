package pl.dmuszynski.libso.service.implementation;

import pl.dmuszynski.libso.repository.CategoryRepository;
import pl.dmuszynski.libso.validator.CategoryValidator;
import pl.dmuszynski.libso.service.CategoryService;
import pl.dmuszynski.libso.payload.dto.CategoryDTO;
import pl.dmuszynski.libso.payload.CategoryView;
import pl.dmuszynski.libso.mapper.CategoryMapper;
import pl.dmuszynski.libso.model.Category;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.stream.Collectors;
import java.util.Set;

@RequiredArgsConstructor
@Service(value = "categoryService")
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final CategoryValidator categoryValidator;
    private final CategoryMapper categoryMapper;
    private final EntityManager entityManager;

    @Override
    public CategoryDTO createCategory(CategoryDTO categoryDetails) {
        final Category createdCategory = this.categoryRepository.save(
            Category.builder()
                .name(categoryDetails.getName())
                .parent(this.categoryMapper.mapToModel((CategoryDTO) categoryDetails.getParent()))
                .build()
        );

        return this.categoryMapper.mapToDto(createdCategory);
    }

    @Override
    @Transactional
    public CategoryDTO updateCategoryById(CategoryDTO categoryDetails, Long categoryId) {
        this.categoryValidator.validateModelAndModelDtoIds(categoryId, categoryDetails.getId());
        final Category updatedCategory = this.categoryRepository.save(
            Category.builder()
                .id(categoryDetails.getId())
                .name(categoryDetails.getName())
                .parent(this.categoryMapper.mapToModel((CategoryDTO) categoryDetails.getParent()))
                .build()
        );

        return this.categoryMapper.mapToDto(updatedCategory);
    }

    @Override
    public Set<CategoryView> findAllCategoryDto() {
        return this.categoryRepository.findAll().stream()
            .map(this.categoryMapper::mapToDto)
            .collect(Collectors.toSet());
    }

    @Override
    @Transactional
    public void deleteCategoryById(Long categoryId) {
        this.categoryValidator.validateExistModelById(categoryId);
        this.entityManager.remove(this.entityManager.getReference(Category.class, categoryId));
    }
}
