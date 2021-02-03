package pl.dmuszynski.libso.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
@Getter
@SuperBuilder
@NoArgsConstructor
public class Purchase extends AbstractEntity {

    private int productsAmount;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cart_id")
    private Cart cart;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "offer_id")
    private Offer offer;

    public void setProductsAmount(int productsAmount) {
        this.productsAmount = productsAmount;
    }
}
