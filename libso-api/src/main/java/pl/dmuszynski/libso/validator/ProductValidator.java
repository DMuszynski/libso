package pl.dmuszynski.libso.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;
import pl.dmuszynski.libso.model.Product;

@Component
public class ProductValidator extends AbstractModelValidator<Product> {
    @Autowired
    public ProductValidator(CrudRepository<Product, Long> repository) {
        super(repository);
    }
}
