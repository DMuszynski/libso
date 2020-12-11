package pl.dmuszynski.libso.model;

import lombok.experimental.SuperBuilder;
import lombok.NoArgsConstructor;
import lombok.Getter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Set;

@Entity
@Getter
@SuperBuilder
@NoArgsConstructor
public class Product extends AbstractEntity {

    private String name;

    private String model;

    private String brand;

    private String description;

    private BigDecimal price;

//    @OneToOne(mappedBy = "product", fetch = FetchType.LAZY)
//    private Offer offer;

    @OneToOne(mappedBy = "product", fetch = FetchType.LAZY)
    private Promotion promotion;

    @OneToMany(mappedBy = "product")
    private Set<Review> reviews;

    @ManyToMany(mappedBy = "products")
    private Set<Transaction> transactions;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "product_category", joinColumns = @JoinColumn(name = "product_id", referencedColumnName = "id"),
        inverseJoinColumns = @JoinColumn(name = "category_id", referencedColumnName = "id"))
    private Set<Category> categories;
}
