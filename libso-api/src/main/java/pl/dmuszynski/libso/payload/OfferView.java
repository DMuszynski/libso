package pl.dmuszynski.libso.payload;

import org.springframework.data.rest.core.config.Projection;
import pl.dmuszynski.libso.model.Offer;

import java.time.LocalDateTime;
import java.time.LocalDate;

@Projection(name = "offerView", types = Offer.class)
public interface OfferView {
    int getQuantity();
    LocalDate getEndDate();
    LocalDateTime getCreationDate();
}
