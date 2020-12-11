package pl.dmuszynski.libso.repository;

import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.repository.CrudRepository;
import pl.dmuszynski.libso.payload.ReviewView;
import pl.dmuszynski.libso.model.Review;

import java.util.List;

@RepositoryRestResource(collectionResourceRel = "reviews", path = "reviews", excerptProjection = ReviewView.class)
public interface ReviewRepository extends CrudRepository<Review, Long> {
    List<ReviewView> findAllReviewViewByProductId(Long productId);
}
