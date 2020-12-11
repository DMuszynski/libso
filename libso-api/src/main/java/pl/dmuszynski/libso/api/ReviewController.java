package pl.dmuszynski.libso.api;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import pl.dmuszynski.libso.service.ReviewService;
import pl.dmuszynski.libso.payload.dto.ReviewDTO;
import pl.dmuszynski.libso.payload.ReviewView;
import lombok.RequiredArgsConstructor;

import java.util.Set;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "libso/products/{productId}/reviews")
public class ReviewController {

    private final ReviewService reviewService;

    @PostMapping
    @PreAuthorize(value = "hasRole('USER')")
    public ResponseEntity<ReviewView> createProductReview(@RequestBody ReviewDTO productReviewDetails, @PathVariable Long productId) {
        final ReviewDTO createdProductReviewDto = this.reviewService.createProductReview(productReviewDetails, productId);
        return new ResponseEntity<>(createdProductReviewDto, HttpStatus.CREATED);
    }

    @PutMapping(value = "/{reviewId}")
    @PreAuthorize(value = "hasRole('USER')")
    public ResponseEntity<ReviewView> updateProductReviewById(@RequestBody ReviewDTO productReviewDetails, @PathVariable Long productId, @PathVariable Long reviewId) {
        final ReviewDTO updatedProductReviewDto = this.reviewService.updateProductReviewById(productReviewDetails, productId, reviewId);
        return new ResponseEntity<>(updatedProductReviewDto, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<Set<ReviewView>> findAllProductReviewsDtoById(@PathVariable Long productId) {
        final Set<ReviewView> foundProductReviewViewSet = this.reviewService.findAllProductReviewDtoByProductId(productId);
        if (!foundProductReviewViewSet.isEmpty())
            return new ResponseEntity<>(foundProductReviewViewSet, HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping(value = "/{reviewId}")
    @PreAuthorize(value = "hasRole('USER')")
    public ResponseEntity<HttpStatus> deleteProductReviewById(@PathVariable Long productId, @PathVariable Long reviewId) {
        this.reviewService.deleteProductReviewById(productId, reviewId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
