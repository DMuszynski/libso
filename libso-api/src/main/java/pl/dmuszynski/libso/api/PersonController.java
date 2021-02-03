package pl.dmuszynski.libso.api;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import pl.dmuszynski.libso.service.PersonService;
import pl.dmuszynski.libso.payload.dto.PersonDTO;
import pl.dmuszynski.libso.payload.PersonView;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@PreAuthorize(value = "hasRole('USER')")
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(value = "libso/users/{userId}/persons")
public class PersonController {

    private final PersonService personService;

    @PostMapping
    public ResponseEntity<PersonView> createUserPerson(@RequestBody PersonDTO userPersonDetails, @PathVariable Long userId) {
        final PersonDTO createdPersonDto = this.personService.createUserPerson(userPersonDetails, userId);
        return new ResponseEntity<>(createdPersonDto, HttpStatus.CREATED);
    }

    @PutMapping(value = "/{personId}")
    public ResponseEntity<PersonView> updateUserPersonById(@RequestBody PersonDTO userPersonDetails, @PathVariable Long userId, @PathVariable Long personId) {
        final PersonDTO updatedPersonDto = this.personService.updateUserPersonById(userPersonDetails, userId, personId);
        return new ResponseEntity<>(updatedPersonDto, HttpStatus.OK);
    }

    @GetMapping(value = "/{personId}")
    public ResponseEntity<PersonView> findUserPersonViewById(@PathVariable Long personId) {
        final PersonView foundPersonView = this.personService.findUserPersonViewById(personId);
        return new ResponseEntity<>(foundPersonView, HttpStatus.OK);
    }

    @DeleteMapping(value = "/{personId}")
    public ResponseEntity<HttpStatus> deleteUserPersonById(@PathVariable Long userId , @PathVariable Long personId) {
        this.personService.deleteUserPersonById(userId, personId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

