package pl.dmuszynski.libso.repository;

import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import pl.dmuszynski.libso.payload.ProductView;
import pl.dmuszynski.libso.model.Product;

@RepositoryRestResource(collectionResourceRel = "products", path = "products", excerptProjection = ProductView.class)
public interface ProductRepository extends JpaRepository<Product, Long> {
    @Query(
        value = "SELECT DISTINCT p.* FROM product p INNER JOIN product_category pc on p.id = pc.product_id INNER JOIN category c on pc.category_id = :categoryId",
        countQuery = "SELECT count(*) FROM product",
        nativeQuery = true)
    Page<Product> findAllByCategoryId(@Param("categoryId") Long categoryId, Pageable pageable);
}
