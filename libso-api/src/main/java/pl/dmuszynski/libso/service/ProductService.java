package pl.dmuszynski.libso.service;

import pl.dmuszynski.libso.payload.dto.ProductDTO;
import pl.dmuszynski.libso.payload.ProductView;

import java.util.Set;

public interface ProductService {
    ProductDTO createProduct(ProductDTO productDetails);
    ProductDTO updateProductById(ProductDTO productDetails, Long productId);
    Set<ProductView> findAllProductViewByCategoryId(Long categoryId, int page, int size, String sortBy);
    void deleteProductById(Long productId);
}
