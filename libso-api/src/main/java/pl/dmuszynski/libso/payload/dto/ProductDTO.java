package pl.dmuszynski.libso.payload.dto;

import pl.dmuszynski.libso.payload.ProductView;
import lombok.experimental.SuperBuilder;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@SuperBuilder
@NoArgsConstructor
public class ProductDTO extends AbstractDTO implements ProductView {

    private String name;

    private String model;

    private String brand;

    private String description;

    private BigDecimal price;

    private Set<ImageDTO> images = new HashSet<>();

    private Set<ReviewDTO> reviews = new HashSet<>();

    private Set<PromotionDTO> promotions = new HashSet<>();

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

    @Override
    public Set<ImageDTO> getImages() {
        return this.images;
    }

    @Override
    public Set<ReviewDTO> getReviews() {
        return this.reviews;
    }

    @Override
    public Set<PromotionDTO> getPromotions() {
        return this.promotions;
    }
}
