package pl.dmuszynski.libso.validator;

import org.springframework.data.repository.CrudRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.dmuszynski.libso.model.User;

@Component
public class AdminValidator extends AbstractModelValidator<User> {
    @Autowired
    public AdminValidator(@Qualifier("adminRepository") CrudRepository<User, Long> repository) {
        super(repository);
    }
}
