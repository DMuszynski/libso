package pl.dmuszynski.libso.model;

import lombok.experimental.SuperBuilder;
import lombok.NoArgsConstructor;
import lombok.Getter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Getter
@SuperBuilder
@NoArgsConstructor
public class Category extends AbstractEntity {

    private String name;

    @OneToOne(fetch = FetchType.LAZY)
    private Category parent;

    @ManyToMany(mappedBy = "categories")
    private Set<Product> products;
}
