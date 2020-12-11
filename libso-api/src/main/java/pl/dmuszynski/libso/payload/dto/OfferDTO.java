package pl.dmuszynski.libso.payload.dto;

import pl.dmuszynski.libso.payload.ProductView;
import pl.dmuszynski.libso.payload.OfferView;
import lombok.experimental.SuperBuilder;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.time.LocalDate;

@SuperBuilder
@NoArgsConstructor
public class OfferDTO extends AbstractDTO implements OfferView {

    private int quantity;

    private LocalDate endDate;

    private LocalDateTime creationDate;

    private ProductDTO product;

    @Override
    public int getQuantity() {
        return this.quantity;
    }

    @Override
    public LocalDate getEndDate() {
        return this.endDate;
    }

    @Override
    public LocalDateTime getCreationDate() {
        return this.creationDate;
    }

    public ProductDTO getProduct() {
        return this.product;
    }
}
