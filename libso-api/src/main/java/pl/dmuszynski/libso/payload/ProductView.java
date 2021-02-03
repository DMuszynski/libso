package pl.dmuszynski.libso.payload;

import org.springframework.data.rest.core.config.Projection;
import pl.dmuszynski.libso.model.Product;
import pl.dmuszynski.libso.payload.dto.ImageDTO;
import pl.dmuszynski.libso.payload.dto.PromotionDTO;
import pl.dmuszynski.libso.payload.dto.ReviewDTO;

import java.math.BigDecimal;
import java.util.Set;

@Projection(name = "productView", types = Product.class)
public interface ProductView extends EntityView {
    String getName();
    String getModel();
    String getBrand();
    String getDescription();
    BigDecimal getPrice();
    Set<ImageDTO> getImages();
    Set<ReviewDTO> getReviews();
    Set<PromotionDTO> getPromotions();
}
