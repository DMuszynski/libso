package pl.dmuszynski.libso.model;

import lombok.experimental.SuperBuilder;
import lombok.NoArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;
import java.util.Set;
import javax.persistence.*;

@Entity
@Getter
@SuperBuilder
@NoArgsConstructor
public class Promotion extends AbstractEntity {

    private String name;

    private int percentValue;

    private LocalDate startDate;

    private LocalDate endDate;

    @ManyToMany
    @JoinTable(name = "product_promotion", joinColumns = @JoinColumn(name = "promotion_id", referencedColumnName = "id"),
        inverseJoinColumns = @JoinColumn(name = "product_id", referencedColumnName = "id"))
    private Set<Product> products;

//    @ManyToMany(mappedBy = "promotions")
//    private Set<Product> product;


    public void setProducts(Set<Product> products) {
        this.products = products;
    }
}
