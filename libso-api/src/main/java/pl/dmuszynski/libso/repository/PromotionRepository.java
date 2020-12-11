package pl.dmuszynski.libso.repository;

import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.jpa.repository.JpaRepository;
import pl.dmuszynski.libso.model.Promotion;

@RepositoryRestResource(collectionResourceRel = "promotions", path = "promotions")
public interface PromotionRepository extends JpaRepository<Promotion, Long> {

}
