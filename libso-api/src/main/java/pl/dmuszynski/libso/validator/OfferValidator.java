package pl.dmuszynski.libso.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;
import pl.dmuszynski.libso.model.Offer;

@Component
public class OfferValidator extends AbstractModelValidator<Offer> {
    @Autowired
    public OfferValidator(CrudRepository<Offer, Long> repository) {
        super(repository);
    }
}
