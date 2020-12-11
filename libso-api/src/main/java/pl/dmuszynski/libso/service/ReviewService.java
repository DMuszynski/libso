package pl.dmuszynski.libso.service;

import pl.dmuszynski.libso.payload.dto.ReviewDTO;
import pl.dmuszynski.libso.payload.ReviewView;

import java.util.Set;

public interface ReviewService {
    ReviewDTO createProductReview(ReviewDTO productReviewDetails, Long productId);
    ReviewDTO updateProductReviewById(ReviewDTO productReviewDetails, Long productId, Long reviewId);
    Set<ReviewView> findAllProductReviewDtoByProductId(Long productId);
    void deleteProductReviewById(Long productId, Long reviewId);
}
