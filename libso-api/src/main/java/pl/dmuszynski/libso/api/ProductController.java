package pl.dmuszynski.libso.api;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import pl.dmuszynski.libso.payload.ProductView;
import pl.dmuszynski.libso.payload.dto.ProductDTO;
import pl.dmuszynski.libso.service.ProductService;

import lombok.RequiredArgsConstructor;

import java.io.IOException;
import java.util.Set;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "libso/products")
@CrossOrigin(origins = "http://localhost:4200")
public class ProductController {

    private final ProductService productService;

    @PostMapping
    @PreAuthorize(value = "hasRole('MODERATOR')")
    public ResponseEntity<ProductView> createProduct(@RequestBody ProductDTO productDetails) {
        final ProductView createdProductDto = this.productService.createProduct(productDetails);
        return new ResponseEntity<>(createdProductDto, HttpStatus.CREATED);
    }

    @PutMapping(value = "/{productId}")
    @PreAuthorize(value = "hasRole('MODERATOR')")
    public ResponseEntity<ProductView> updateProductById(@RequestBody ProductDTO productDetails, @PathVariable Long productId) {
        final ProductView updatedProductDto = this.productService.updateProductById(productDetails, productId);
        return new ResponseEntity<>(updatedProductDto, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<Set<ProductView>> findAllProductView() {
        final Set<ProductView> foundProductDtoList = this.productService.findAllProductView();
        if (!foundProductDtoList.isEmpty())
            return new ResponseEntity<>(foundProductDtoList, HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping(value = "/categories/{categoryId}")
    public ResponseEntity<Set<ProductView>> findAllProductViewByCategoryId(@PathVariable Long categoryId, @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "5") int size, @RequestParam(defaultValue = "id") String sortBy)
    {
        final Set<ProductView> foundProductDtoList = this.productService.findAllProductViewByCategoryId(categoryId, page, size, sortBy);
        if (!foundProductDtoList.isEmpty())
            return new ResponseEntity<>(foundProductDtoList, HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping(value = "/{productId}")
    @PreAuthorize(value = "hasRole('MODERATOR')")
    public ResponseEntity<HttpStatus> deleteProductById(@PathVariable Long productId) {
        this.productService.deleteProductById(productId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
