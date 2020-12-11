package pl.dmuszynski.libso.payload.dto;

import pl.dmuszynski.libso.payload.PersonView;
import lombok.experimental.SuperBuilder;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@SuperBuilder
@NoArgsConstructor
public class PersonDTO extends AbstractDTO implements PersonView {

    private String name;

    private String surname;

    private String phoneNumber;

    private LocalDate dateOfBirth;

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public String getSurname() {
        return this.surname;
    }

    @Override
    public String getPhoneNumber() {
        return this.phoneNumber;
    }

    @Override
    public LocalDate getDateOfBirth() {
        return this.dateOfBirth;
    }
}
