package pl.dmuszynski.libso.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import pl.dmuszynski.libso.model.Cart;
import pl.dmuszynski.libso.payload.CartView;

@RepositoryRestResource(collectionResourceRel = "carts", path = "carts")
public interface CartRepository extends JpaRepository<Cart, Long> {
    Cart findByUserId(Long id);
}
