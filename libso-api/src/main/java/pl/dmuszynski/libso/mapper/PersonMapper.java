package pl.dmuszynski.libso.mapper;

import pl.dmuszynski.libso.payload.dto.PersonDTO;
import pl.dmuszynski.libso.model.Person;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PersonMapper {
    PersonDTO mapToDTO(Person mappedPerson);
}
