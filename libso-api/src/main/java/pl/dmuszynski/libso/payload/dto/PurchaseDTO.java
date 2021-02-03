package pl.dmuszynski.libso.payload.dto;

import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import pl.dmuszynski.libso.payload.PurchaseView;

@SuperBuilder
@NoArgsConstructor
public class PurchaseDTO extends AbstractDTO implements PurchaseView {

    private Long cartId;

    private int productsAmount;

    private OfferDTO offer;

    @Override
    public int getProductsAmount() {
        return this.productsAmount;
    }

    @Override
    public OfferDTO getOffer() {
        return this.offer;
    }

    public Long getCartId() {
        return cartId;
    }
}
