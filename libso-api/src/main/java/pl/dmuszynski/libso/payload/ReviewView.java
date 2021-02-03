package pl.dmuszynski.libso.payload;

import org.springframework.data.rest.core.config.Projection;
import pl.dmuszynski.libso.model.Review;

import java.time.LocalDateTime;

@Projection(name = "reviewView", types = Review.class)
public interface ReviewView extends EntityView {
    int getPlusRate();
    int getMinusRate();
    String getReviewComment();
    LocalDateTime getCreationDate();
}
