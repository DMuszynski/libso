package pl.dmuszynski.libso.repository;

import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.repository.CrudRepository;
import pl.dmuszynski.libso.model.Person;
import pl.dmuszynski.libso.payload.PersonView;

import java.util.Optional;

@RepositoryRestResource(collectionResourceRel = "persons", path = "persons", excerptProjection = PersonView.class)
public interface PersonRepository extends CrudRepository<Person, Long> {
    Optional<PersonView> findUserPersonViewById(Long id);
}
