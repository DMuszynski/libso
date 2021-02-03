package pl.dmuszynski.libso.service.implementation;

import pl.dmuszynski.libso.model.User;
import pl.dmuszynski.libso.repository.ReviewRepository;
import pl.dmuszynski.libso.validator.ProductValidator;
import pl.dmuszynski.libso.validator.ReviewValidator;
import pl.dmuszynski.libso.payload.dto.ReviewDTO;
import pl.dmuszynski.libso.payload.ReviewView;
import pl.dmuszynski.libso.service.ReviewService;
import pl.dmuszynski.libso.mapper.ReviewMapper;
import pl.dmuszynski.libso.model.Product;
import pl.dmuszynski.libso.model.Review;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.Set;

@RequiredArgsConstructor
@Service(value = "reviewService")
public class ReviewServiceImpl implements ReviewService {

    private final ReviewRepository reviewRepository;
    private final ReviewValidator reviewValidator;
    private final ReviewMapper reviewMapper;
    private final ProductValidator productValidator;
    private final EntityManager entityManager;

    @Override
    @Transactional
    public ReviewDTO createProductReview(ReviewDTO productReviewDetails, Long productId) {
        this.productValidator.validateExistModelById(productId);
        final Review createdProductReview = this.reviewRepository.save(
            Review.builder()
                .plusRate(productReviewDetails.getPlusRate())
                .minusRate(productReviewDetails.getMinusRate())
                .reviewComment(productReviewDetails.getReviewComment())
                .product(this.entityManager.getReference(Product.class, productId))
                .user(this.entityManager.getReference(User.class, productReviewDetails.getUsernameDTO().getId()))
                .build()
        );

        return this.reviewMapper.mapToDto(createdProductReview);
    }

    @Override
    @Transactional
    public ReviewDTO updateProductReviewById(ReviewDTO productReviewDetails, Long productId, Long reviewId) {
        this.reviewValidator.validateProductReviewIds(reviewId, productReviewDetails.getId(), productId);
        final Review updatedProductReview = this.reviewRepository.save(
            Review.builder()
                .id(productReviewDetails.getId())
                .plusRate(productReviewDetails.getPlusRate())
                .minusRate(productReviewDetails.getMinusRate())
                .reviewComment(productReviewDetails.getReviewComment())
                .creationDate(productReviewDetails.getCreationDate())
                .product(this.entityManager.getReference(Product.class, productId))
                .user(this.entityManager.getReference(User.class, productReviewDetails.getUsernameDTO().getId()))
                .build()
        );

        return this.reviewMapper.mapToDto(updatedProductReview);
    }

    @Override
    @Transactional
    public ReviewDTO updateReviewPlusRate(Long productId, Long reviewId) {
        final Review updateReview = this.reviewRepository.findById(reviewId).orElseThrow();
        updateReview.setPlusRate(updateReview.getPlusRate() + 1);
        return this.reviewMapper.mapToDto(updateReview);
    }

    @Override
    @Transactional
    public ReviewDTO updateReviewMinusRate(Long productId, Long reviewId) {
        final Review updateReview = this.reviewRepository.findById(reviewId).orElseThrow();
        updateReview.setMinusRate(updateReview.getMinusRate() + 1);
        return this.reviewMapper.mapToDto(updateReview);
    }

    @Override
    public Set<ReviewView> findAllProductReviewDtoByProductId(Long productId) {
        return new HashSet<>(this.reviewRepository.findAllReviewViewByProductId(productId));
    }

    @Override
    @Transactional
    public void deleteProductReviewById(Long productId, Long reviewId) {
        this.reviewValidator.validateProductReviewIds(reviewId, productId);
        this.entityManager.remove(this.entityManager.getReference(Review.class, reviewId));
    }
}
