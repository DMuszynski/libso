package pl.dmuszynski.libso.service;

import pl.dmuszynski.libso.payload.dto.PersonDTO;
import pl.dmuszynski.libso.payload.PersonView;

public interface PersonService {
    PersonDTO createUserPerson(PersonDTO userPersonDetails, Long userId);
    PersonDTO updateUserPersonById(PersonDTO userPersonDetails, Long userId, Long personId);
    PersonView findUserPersonViewById(Long personId);
    void deleteUserPersonById(Long userId, Long personId);
}
