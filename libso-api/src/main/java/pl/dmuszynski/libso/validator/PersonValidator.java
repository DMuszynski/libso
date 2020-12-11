package pl.dmuszynski.libso.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Component;
import pl.dmuszynski.libso.exception.IncorrectIdException;
import pl.dmuszynski.libso.model.Person;

@Component
public class PersonValidator extends AbstractModelWithOwnerValidator<Person> {

    @Autowired
    public PersonValidator(CrudRepository<Person, Long> repository) {
        super(repository);
    }

    public void validateUserPersonIds(Long personId, Long personDTOId, Long userId) throws ResourceNotFoundException, IncorrectIdException {
        final Person validatePerson = this.validateWithReturnModelAndModelDtoIds(personId, personDTOId);
        this.equalsModelOwnerAndRequestModelOwnerIds(validatePerson.getUser(), userId);
    }

    public void validateUserPersonIds(Long personId, Long userId) throws ResourceNotFoundException, IncorrectIdException {
        final Person validatePerson = this.validateFindModelById(personId);
        this.equalsModelOwnerAndRequestModelOwnerIds(validatePerson.getUser(), userId);
    }
}
