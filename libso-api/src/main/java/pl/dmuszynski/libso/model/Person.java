package pl.dmuszynski.libso.model;

import lombok.experimental.SuperBuilder;
import lombok.NoArgsConstructor;
import lombok.Getter;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Getter
@SuperBuilder
@NoArgsConstructor
public class Person extends AbstractEntity {

    private String name;

    private String surname;

    private String phoneNumber;

    private LocalDate dateOfBirth;

    @MapsId
    @OneToOne(fetch = FetchType.LAZY)
    private User user;
}