package pl.dmuszynski.libso.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;
import pl.dmuszynski.libso.exception.IncorrectIdException;
import pl.dmuszynski.libso.model.AbstractEntity;

@Component
public abstract class AbstractModelWithOwnerValidator<T extends AbstractEntity> extends AbstractModelValidator<T> {

    @Autowired
    public AbstractModelWithOwnerValidator(CrudRepository<T, Long> repository) {
        super(repository);
    }

    protected void equalsModelOwnerAndRequestModelOwnerIds(AbstractEntity owner, Long ownerId) {
        if (!owner.getId().equals(ownerId))
            throw new IncorrectIdException("You enter incorrect " + owner.getClass().getName() + " id: " + ownerId);
    }
}
