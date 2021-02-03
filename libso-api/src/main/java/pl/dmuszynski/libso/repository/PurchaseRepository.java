package pl.dmuszynski.libso.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import pl.dmuszynski.libso.model.Purchase;

@RepositoryRestResource(collectionResourceRel = "purchases", path = "purchases")
public interface PurchaseRepository extends JpaRepository<Purchase, Long> {
    void deleteAllByCartId(Long cartId);
    Purchase findByOfferIdAndCartId(Long offerId, Long cartId);
}
