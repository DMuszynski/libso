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

    @ManyToMany
    @JoinTable(name = "product_category", joinColumns = @JoinColumn(name = "category_id", referencedColumnName = "id"),
        inverseJoinColumns = @JoinColumn(name = "product_id", referencedColumnName = "id"))
    private Set<Product> products;

    public void setProducts(Set<Product> products) {
        this.products = products;
    }
}
