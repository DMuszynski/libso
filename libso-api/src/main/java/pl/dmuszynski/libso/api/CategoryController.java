package pl.dmuszynski.libso.api;

import org.springframework.security.access.prepost.PreAuthorize;
import pl.dmuszynski.libso.payload.CategoryView;
import pl.dmuszynski.libso.payload.ProductView;
import pl.dmuszynski.libso.service.CategoryService;
import pl.dmuszynski.libso.payload.dto.CategoryDTO;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;

import lombok.RequiredArgsConstructor;

import java.util.Set;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "libso/categories")
public class CategoryController {

    private final CategoryService categoryService;

    @PostMapping
    @PreAuthorize(value = "hasRole('MODERATOR')")
    public ResponseEntity<CategoryView> createCategory(@RequestBody CategoryDTO categoryDetails) {
        final CategoryDTO createdCategoryDto = this.categoryService.createCategory(categoryDetails);
        return new ResponseEntity<>(createdCategoryDto, HttpStatus.CREATED);
    }

    @PutMapping(value = "/{categoryId}")
    @PreAuthorize(value = "hasRole('MODERATOR')")
    public ResponseEntity<CategoryView> updateCategoryById(@RequestBody CategoryDTO categoryDetails, @PathVariable Long categoryId) {
        final CategoryDTO createdCategoryDto = this.categoryService.updateCategoryById(categoryDetails, categoryId);
        return new ResponseEntity<>(createdCategoryDto, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<Set<CategoryView>> findAllCategoryDto(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "5") int size, @RequestParam(defaultValue = "id") String sortBy) {
        final Set<CategoryView> foundCategoryDtoList = this.categoryService.findAllCategoryDto(page, size, sortBy);
        if (!foundCategoryDtoList.isEmpty())
            return new ResponseEntity<>(foundCategoryDtoList, HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping(value = "/{categoryId}")
    @PreAuthorize(value = "hasRole('MODERATOR')")
    public ResponseEntity<HttpStatus> deleteCategoryById(@PathVariable Long categoryId) {
        this.categoryService.deleteCategoryById(categoryId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
