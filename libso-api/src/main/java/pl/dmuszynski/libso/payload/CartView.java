package pl.dmuszynski.libso.payload;

import org.springframework.data.rest.core.config.Projection;
import pl.dmuszynski.libso.model.Cart;
import pl.dmuszynski.libso.payload.dto.OfferDTO;
import pl.dmuszynski.libso.payload.dto.PurchaseDTO;

import java.math.BigDecimal;
import java.util.Set;

@Projection(name = "cartView", types = Cart.class)
public interface CartView extends EntityView{
    int getCapacity();
    BigDecimal getTotalPrice();
    Set<PurchaseDTO> getPurchases();
}
