package pl.dmuszynski.libso.validator;

import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;
import pl.dmuszynski.libso.exception.IncorrectIdException;
import pl.dmuszynski.libso.model.Promotion;

@Component
public class PromotionValidator extends AbstractModelWithOwnerValidator<Promotion> {

    @Autowired
    public PromotionValidator(CrudRepository<Promotion, Long> repository) {
        super(repository);
    }

    public void validateProductPromotionIds(Long promotionId, Long promotionDTOId, Long productId) throws ResourceNotFoundException, IncorrectIdException {
        final Promotion validatePromotion = this.validateWithReturnModelAndModelDtoIds(promotionId, promotionDTOId);
        this.equalsModelOwnerAndRequestModelOwnerIds(validatePromotion.getProduct(), productId);
    }

    public void validateProductPromotionIds(Long promotionId, Long productId) throws ResourceNotFoundException, IncorrectIdException {
        final Promotion validatePromotion = this.validateFindModelById(promotionId);
        this.equalsModelOwnerAndRequestModelOwnerIds(validatePromotion.getProduct(), productId);
    }
}
