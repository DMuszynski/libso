package pl.dmuszynski.libso.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;
import pl.dmuszynski.libso.model.Promotion;

@Component
public class PromotionValidator extends AbstractModelValidator<Promotion> {
    @Autowired
    public PromotionValidator(CrudRepository<Promotion, Long> repository) {
        super(repository);
    }
}
