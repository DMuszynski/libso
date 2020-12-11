package pl.dmuszynski.libso.model;

import lombok.experimental.SuperBuilder;
import lombok.NoArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;
import javax.persistence.*;

@Entity
@Getter
@SuperBuilder
@NoArgsConstructor
public class Promotion extends AbstractEntity {

    private int percentValue;

    private LocalDate startDate;

    private LocalDate endDate;

    @MapsId
    @OneToOne(fetch = FetchType.LAZY)
    private Product product;
}
