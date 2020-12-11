package pl.dmuszynski.libso.model;

import lombok.experimental.SuperBuilder;
import lombok.NoArgsConstructor;
import lombok.Getter;

import javax.persistence.*;

@Entity
@Getter
@SuperBuilder
@NoArgsConstructor
public class Review extends AbstractEntity {

    private int grade;

    private String reviewComment;

    @ManyToOne(fetch = FetchType.LAZY)
    private Product product;
}
