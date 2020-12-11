package pl.dmuszynski.libso.payload;

import org.springframework.data.rest.core.config.Projection;
import pl.dmuszynski.libso.model.Person;

import java.time.LocalDate;

@Projection(name = "personView", types = Person.class)
public interface PersonView extends EntityView {
    String getName();
    String getSurname();
    String getPhoneNumber();
    LocalDate getDateOfBirth();
}
