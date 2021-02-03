package pl.dmuszynski.libso.payload;

import org.springframework.data.rest.core.config.Projection;
import pl.dmuszynski.libso.model.Purchase;
import pl.dmuszynski.libso.payload.dto.OfferDTO;

@Projection(name = "cartView", types = Purchase.class)
public interface PurchaseView {
    int getProductsAmount();
    OfferDTO getOffer();
}
