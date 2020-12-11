package pl.dmuszynski.libso.payload;

import org.springframework.data.rest.core.config.Projection;
import pl.dmuszynski.libso.model.Product;

import java.math.BigDecimal;

@Projection(name = "productView", types = Product.class)
public interface ProductView extends EntityView {
    String getName();
    String getModel();
    String getBrand();
    String getDescription();
    BigDecimal getPrice();
}
