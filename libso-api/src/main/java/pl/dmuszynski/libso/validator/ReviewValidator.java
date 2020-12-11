package pl.dmuszynski.libso.validator;

import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;
import pl.dmuszynski.libso.exception.IncorrectIdException;
import pl.dmuszynski.libso.model.Review;

@Component
public class ReviewValidator extends AbstractModelWithOwnerValidator<Review> {

    @Autowired
    public ReviewValidator(CrudRepository<Review, Long> repository) {
        super(repository);
    }

    public void validateProductReviewIds(Long reviewId, Long reviewDTOId, Long productId) throws ResourceNotFoundException, IncorrectIdException {
        final Review validateReview = this.validateWithReturnModelAndModelDtoIds(reviewId, reviewDTOId);
        this.equalsModelOwnerAndRequestModelOwnerIds(validateReview.getProduct(), productId);
    }

    public Review validateProductReviewIds(Long reviewId, Long productId) throws ResourceNotFoundException, IncorrectIdException {
        final Review validateReview = this.validateFindModelById(reviewId);
        this.equalsModelOwnerAndRequestModelOwnerIds(validateReview.getProduct(), productId);

        return validateReview;
    }
}
