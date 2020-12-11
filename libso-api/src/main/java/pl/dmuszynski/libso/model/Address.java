package pl.dmuszynski.libso.model;

import lombok.experimental.SuperBuilder;
import lombok.NoArgsConstructor;
import lombok.Getter;

import javax.persistence.*;

@Entity
@Getter
@SuperBuilder
@NoArgsConstructor
public class Address extends AbstractEntity {

    private String country;

    private String location;

    private String zipCode;

    private String street;

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;
}
