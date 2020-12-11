package pl.dmuszynski.libso.service.implementation;

import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import pl.dmuszynski.libso.repository.PersonRepository;
import pl.dmuszynski.libso.validator.PersonValidator;
import pl.dmuszynski.libso.validator.UserValidator;
import pl.dmuszynski.libso.service.PersonService;
import pl.dmuszynski.libso.payload.dto.PersonDTO;
import pl.dmuszynski.libso.payload.PersonView;
import pl.dmuszynski.libso.mapper.PersonMapper;
import pl.dmuszynski.libso.model.Person;
import pl.dmuszynski.libso.model.User;
import lombok.RequiredArgsConstructor;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

@RequiredArgsConstructor
@Service(value = "personService")
public class PersonServiceImpl implements PersonService {

    private final PersonRepository personRepository;
    private final PersonValidator personValidator;
    private final UserValidator userValidator;
    private final EntityManager entityManager;
    private final PersonMapper personMapper;

    @Override
    @Transactional
    public PersonDTO createUserPerson(PersonDTO userPersonDetails, Long userId) {
        this.userValidator.validateExistModelById(userId);
        final Person savedUserPerson = this.personRepository
            .save(Person.builder()
                .user(this.entityManager.getReference(User.class, userId))
                .name(userPersonDetails.getName())
                .surname(userPersonDetails.getSurname())
                .phoneNumber(userPersonDetails.getPhoneNumber())
                .dateOfBirth(userPersonDetails.getDateOfBirth())
                .build()
            );

        return this.personMapper.mapToDTO(savedUserPerson);
    }

    @Override
    @Transactional
    public PersonDTO updateUserPersonById(PersonDTO userPersonDetails, Long userId, Long personId) {
       this.personValidator.validateUserPersonIds(personId, userPersonDetails.getId(), userId);
        final Person updatedUserPerson = this.personRepository
            .save(Person.builder()
                .id(personId)
                .user(this.entityManager.getReference(User.class, userId))
                .name(userPersonDetails.getName())
                .surname(userPersonDetails.getSurname())
                .phoneNumber(userPersonDetails.getPhoneNumber())
                .dateOfBirth(userPersonDetails.getDateOfBirth())
                .build()
            );

        return this.personMapper.mapToDTO(updatedUserPerson);
    }

    @Override
    public PersonView findUserPersonViewById(Long personId) {
        return this.personRepository.findUserPersonViewById(personId)
            .orElseThrow(() -> new ResourceNotFoundException("Person not found for this id: " + personId));
    }

    @Override
    @Transactional
    public void deleteUserPersonById(Long userId, Long personId) {
        this.personValidator.validateUserPersonIds(userId, personId);
        this.entityManager.remove(this.entityManager.getReference(Person.class, personId));
    }
}