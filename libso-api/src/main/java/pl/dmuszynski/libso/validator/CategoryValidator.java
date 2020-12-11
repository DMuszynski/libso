package pl.dmuszynski.libso.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;
import pl.dmuszynski.libso.model.Category;

@Component
public class CategoryValidator extends AbstractModelValidator<Category> {
    @Autowired
    public CategoryValidator(CrudRepository<Category, Long> repository) {
        super(repository);
    }
}
