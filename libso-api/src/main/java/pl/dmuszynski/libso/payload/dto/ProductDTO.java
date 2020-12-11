package pl.dmuszynski.libso.payload.dto;

import pl.dmuszynski.libso.model.Promotion;
import pl.dmuszynski.libso.payload.ProductView;
import lombok.experimental.SuperBuilder;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Set;

@SuperBuilder
@NoArgsConstructor
public class ProductDTO extends AbstractDTO implements ProductView {

    private String name;

    private String model;

    private String brand;

    private String description;

    private BigDecimal price;

    private PromotionDTO promotion;

    private Set<CategoryDTO> categories;

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public String getModel() {
        return this.model;
    }

    @Override
    public String getBrand() {
        return this.brand;
    }

    @Override
    public String getDescription() {
        return this.description;
    }

    @Override
    public BigDecimal getPrice() {
        return this.price;
    }

    public PromotionDTO getPromotion() {
        return promotion;
    }

    public Set<CategoryDTO> getCategories() {
        return this.categories;
    }
}
