package pl.dmuszynski.libso.payload.dto;

import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import pl.dmuszynski.libso.model.Purchase;
import pl.dmuszynski.libso.payload.CartView;

import java.math.BigDecimal;
import java.util.Set;

@SuperBuilder
@NoArgsConstructor
public class CartDTO extends AbstractDTO implements CartView {

    private int capacity;

    private BigDecimal totalPrice;

    private Set<PurchaseDTO> purchases;

    @Override
    public int getCapacity() {
        return this.capacity;
    }

    @Override
    public BigDecimal getTotalPrice() {
        return this.totalPrice;
    }

    @Override
    public Set<PurchaseDTO> getPurchases() {
        return this.purchases;
    }
}
