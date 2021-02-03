package pl.dmuszynski.libso.model;

import lombok.Getter;
import lombok.experimental.SuperBuilder;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Set;

@Entity
@Getter
@SuperBuilder
@NoArgsConstructor
public class Cart extends AbstractEntity {

    private int capacity;

    private BigDecimal totalPrice;

    @MapsId
    @OneToOne(fetch = FetchType.LAZY)
    private User user;

    @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL)
    private Set<Purchase> purchases;

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }
}
