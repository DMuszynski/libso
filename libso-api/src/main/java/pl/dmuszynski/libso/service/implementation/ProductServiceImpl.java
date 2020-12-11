package pl.dmuszynski.libso.service.implementation;

import pl.dmuszynski.libso.repository.ProductRepository;
import pl.dmuszynski.libso.validator.ProductValidator;
import pl.dmuszynski.libso.service.ProductService;
import pl.dmuszynski.libso.payload.dto.ProductDTO;
import pl.dmuszynski.libso.payload.ProductView;
import pl.dmuszynski.libso.mapper.CategoryMapper;
import pl.dmuszynski.libso.mapper.ProductMapper;
import pl.dmuszynski.libso.model.Category;
import pl.dmuszynski.libso.model.Product;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.stream.Collectors;
import java.util.Set;

@RequiredArgsConstructor
@Service(value = "productService")
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ProductValidator productValidator;
    private final ProductMapper productMapper;
    private final CategoryMapper categoryMapper;
    private final EntityManager entityManager;

    @Override
    @Transactional
    public ProductDTO createProduct(ProductDTO productDetails) {
        final Set<Category> foundCategories = productDetails.getCategories().stream()
            .map(this.categoryMapper::mapToModel)
            .map(category ->  this.entityManager.getReference(Category.class, category.getId()))
            .collect(Collectors.toSet());

        final Product createdProduct = this.productRepository.save(
            Product.builder()
                .name(productDetails.getName())
                .price(productDetails.getPrice())
                .brand(productDetails.getBrand())
                .model(productDetails.getModel())
                .description(productDetails.getDescription())
                .categories(foundCategories)
                .build()
        );

        return productMapper.mapToDto(createdProduct);
    }

    @Override
    @Transactional
    public ProductDTO updateProductById(ProductDTO productDetails, Long productId) {
        this.productValidator.validateModelAndModelDtoIds(productId, productDetails.getId());
        final Set<Category> foundCategories = productDetails.getCategories().stream()
            .map(this.categoryMapper::mapToModel)
            .map(category ->  this.entityManager.getReference(Category.class,category.getId()))
            .collect(Collectors.toSet());

        final Product updatedProduct = this.productRepository.save(
            Product.builder()
                .id(productId)
                .name(productDetails.getName())
                .price(productDetails.getPrice())
                .brand(productDetails.getBrand())
                .model(productDetails.getModel())
                .description(productDetails.getDescription())
                .categories(foundCategories)
                .build()
        );

        return productMapper.mapToDto(updatedProduct);
    }

    @Override
    @Transactional
    public Set<ProductView> findAllProductViewByCategoryId(Long categoryId, int page, int size, String sortBy) {
        final Pageable paging = PageRequest.of(page, size, Sort.by(sortBy));
        final var foundProductList = this.productRepository.findAllByCategoryId(categoryId, paging);

        return foundProductList.stream()
            .map(this.productMapper::mapToDto)
            .collect(Collectors.toSet());
    }

    @Override
    @Transactional
    public void deleteProductById(Long productId) {
        this.productValidator.validateExistModelById(productId);
        this.entityManager.remove(entityManager.getReference(Product.class, productId));
    }
}
