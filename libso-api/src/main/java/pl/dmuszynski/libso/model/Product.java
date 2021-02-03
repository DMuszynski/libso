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

    @OneToOne(mappedBy = "product", cascade = CascadeType.ALL)
    private Offer offer;

    @OneToMany(mappedBy = "product")
    private Set<Image> images;

    @OneToMany(mappedBy = "product")
    private Set<Review> reviews;

    @ManyToMany(mappedBy = "products")
    private Set<Transaction> transactions;

//    @ManyToMany(fetch = FetchType.EAGER)
//    @JoinTable(name = "product_promotion", joinColumns = @JoinColumn(name = "product_id", referencedColumnName = "id"),
//        inverseJoinColumns = @JoinColumn(name = "promotion_id", referencedColumnName = "id"))
//    private Set<Promotion> promotions;

    @ManyToMany(mappedBy = "products", fetch = FetchType.EAGER)
    private Set<Promotion> promotions;

    @ManyToMany(mappedBy = "products", fetch = FetchType.EAGER)
    private Set<Category> categories;

//    @ManyToMany(fetch = FetchType.EAGER)
//    @JoinTable(name = "product_category", joinColumns = @JoinColumn(name = "product_id", referencedColumnName = "id"),
//        inverseJoinColumns = @JoinColumn(name = "category_id", referencedColumnName = "id"))
//    private Set<Category> categories;

    public void setOffer(Offer offer) {
        this.offer = offer;
    }

    public void setPromotions(Set<Promotion> promotions) {
        this.promotions = promotions;
    }

    public void setCategories(Set<Category> categories) {
        this.categories = categories;
    }
}
