package pl.dmuszynski.libso.mapper;

import pl.dmuszynski.libso.payload.dto.CategoryDTO;
import pl.dmuszynski.libso.model.Category;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CategoryMapper {
    CategoryDTO mapToDto(Category mappedCategory);
    Category mapToModel(CategoryDTO mappedCategoryDTO);
}
