package pl.dmuszynski.libso.payload.dto;

import pl.dmuszynski.libso.payload.OfferView;
import lombok.experimental.SuperBuilder;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@SuperBuilder
@NoArgsConstructor
public class OfferDTO extends AbstractDTO implements OfferView {

    private int quantity;

    private LocalDate endDate;

    private LocalDateTime creationDate;

    private ProductDTO product;

    private Set<CategoryDTO> categories = new HashSet<>();

    private Set<PromotionDTO> promotions = new HashSet<>();

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

    public Set<CategoryDTO> getCategories() { return this.categories; }

    public Set<PromotionDTO> getPromotions() { return this.promotions; }
}
