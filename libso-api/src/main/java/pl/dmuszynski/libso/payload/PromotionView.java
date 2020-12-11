package pl.dmuszynski.libso.payload;

import org.springframework.data.rest.core.config.Projection;
import pl.dmuszynski.libso.model.Promotion;

import java.time.LocalDate;

@Projection(name = "promotionView", types = Promotion.class)
public interface PromotionView extends EntityView {
    int getPercentValue();
    LocalDate getStartDate();
    LocalDate getEndDate();
}
