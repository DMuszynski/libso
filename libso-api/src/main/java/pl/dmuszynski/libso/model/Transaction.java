package pl.dmuszynski.libso.model;

import lombok.experimental.SuperBuilder;
import lombok.NoArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;

@Entity
@Getter
@SuperBuilder
@NoArgsConstructor
public class Transaction extends AbstractEntity {

    private BigDecimal totalPrice;

    private int transactionProductsAmount;

    private LocalDateTime transactionDate;

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    @ManyToMany
    @JoinTable(name = "transaction_product", joinColumns = @JoinColumn(name = "transaction_id", referencedColumnName = "id"),
        inverseJoinColumns = @JoinColumn(name = "product_id", referencedColumnName = "id"))
    private Set<Product> products = new HashSet<>();

    public void setProducts(Set<Product> products) {
        this.products = products;
    }
}
