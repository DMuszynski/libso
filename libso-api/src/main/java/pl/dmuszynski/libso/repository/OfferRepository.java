package pl.dmuszynski.libso.repository;

import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.jpa.repository.JpaRepository;
import pl.dmuszynski.libso.payload.OfferView;
import pl.dmuszynski.libso.model.Offer;

@RepositoryRestResource(collectionResourceRel = "offers", path = "offers", excerptProjection = OfferView.class)
public interface OfferRepository extends JpaRepository<Offer, Long> {
}
