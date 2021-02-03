package pl.dmuszynski.libso.service.implementation;

import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import pl.dmuszynski.libso.mapper.CategoryMapper;
import pl.dmuszynski.libso.mapper.PromotionMapper;
import pl.dmuszynski.libso.mapper.ReviewMapper;
import pl.dmuszynski.libso.model.Category;
import pl.dmuszynski.libso.model.Promotion;
import pl.dmuszynski.libso.payload.dto.*;
import pl.dmuszynski.libso.repository.OfferRepository;
import pl.dmuszynski.libso.repository.ProductRepository;
import pl.dmuszynski.libso.service.ImageService;
import pl.dmuszynski.libso.service.ProductService;
import pl.dmuszynski.libso.validator.OfferValidator;
import pl.dmuszynski.libso.service.OfferService;
import pl.dmuszynski.libso.mapper.OfferMapper;
import pl.dmuszynski.libso.model.Product;
import pl.dmuszynski.libso.model.Offer;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Transactional
@RequiredArgsConstructor
@Service(value = "offerService")
public class OfferServiceImpl implements OfferService {

    private final OfferRepository offerRepository;
    private final OfferValidator offerValidator;
    private final CategoryMapper categoryMapper;
    private final PromotionMapper promotionMapper;
    private final ReviewMapper reviewMapper;
    private final EntityManager entityManager;
    private final ProductRepository productRepository;
    private final ImageService imageService;

    @Override
    @Transactional
    public Offer updatePurchaseOffer(PurchaseDTO purchaseDetails) {
        final Offer updatedOffer = this.offerRepository.findById(purchaseDetails.getOffer().getId())
            .orElseThrow(ResourceNotFoundException::new);
        updatedOffer.setQuantity(updatedOffer.getQuantity() - purchaseDetails.getProductsAmount());

        return updatedOffer;
    }

    @Override
    @Transactional
    public OfferDTO createOffer(OfferDTO offerDetails) {
        final Product product = this.entityManager.getReference(Product.class, offerDetails.getProduct().getId());
        final Set<Category> foundCategories = offerDetails.getCategories().stream()
            .map(this.categoryMapper::mapToModel)
            .map(category ->  this.entityManager.getReference(Category.class,category.getId()))
            .collect(Collectors.toSet());
        final Set<Promotion> foundPromotions = offerDetails.getPromotions().stream()
            .map(this.promotionMapper::mapToModel)
            .map(promotion ->  this.entityManager.getReference(Promotion.class, promotion.getId()))
            .collect(Collectors.toSet());

        product.setOffer(Offer.builder()
            .quantity(offerDetails.getQuantity())
            .endDate(offerDetails.getEndDate())
            .product(product)
            .build());

        for (Category cat: foundCategories)
            cat.getProducts().add(product);

        for (Promotion pro: foundPromotions)
            pro.getProducts().add(product);

        return offerDetails;
    }

    @Override
    @Transactional
    public OfferDTO updateOfferById(OfferDTO offerDetails, Long offerId) {
        this.offerValidator.validateModelAndModelDtoIds(offerId, offerDetails.getId());
        final Product product = this.entityManager.getReference(Product.class, offerDetails.getProduct().getId());
        final Set<Category> foundCategories = offerDetails.getCategories().stream()
            .map(this.categoryMapper::mapToModel)
            .map(category ->  this.entityManager.getReference(Category.class,category.getId()))
            .collect(Collectors.toSet());
        final Set<Promotion> foundPromotions = offerDetails.getPromotions().stream()
            .map(this.promotionMapper::mapToModel)
            .map(promotion ->  this.entityManager.getReference(Promotion.class, promotion.getId()))
            .collect(Collectors.toSet());

        this.offerRepository.save(Offer.builder()
            .id(offerDetails.getId())
            .quantity(offerDetails.getQuantity())
            .endDate(offerDetails.getEndDate())
            .creationDate(offerDetails.getCreationDate())
            .product(product)
            .build());


        for (Category cat: foundCategories) {
            cat.getProducts().add(product);
        }

        for (Category cat: product.getCategories()) {
            boolean is = false;
            for (Category cat2: foundCategories) {
                if (cat2.equals(cat)) {
                    is = true;
                    break;
                }
            }

            if(!is)
                cat.getProducts().remove(product);
        }

        for (Promotion pro: foundPromotions)
            pro.getProducts().add(product);

        for (Promotion pro: product.getPromotions()) {
            boolean is = false;
            for (Promotion pro2: foundPromotions) {
                if (pro2.equals(pro)) {
                    is = true;
                    break;
                }
            }

            if(!is)
                pro.getProducts().remove(product);
        }

        return offerDetails;
    }

    @Override
    @Transactional
    public Set<OfferDTO> findAllOfferDTO() {
        final Set<Product> foundProduct = new HashSet<>(this.productRepository.findAll());
        final Set<OfferDTO> offersDTO = new HashSet<>();
        for (Product p: foundProduct) {
            if(p.getOffer() != null) {
                offersDTO.add(OfferDTO.builder()
                    .id(p.getId())
                    .endDate(p.getOffer().getEndDate())
                    .quantity(p.getOffer().getQuantity())
                    .creationDate(p.getOffer().getCreationDate())
                    .product(ProductDTO.builder()
                        .id(p.getId())
                        .name(p.getName())
                        .price(p.getPrice())
                        .model(p.getModel())
                        .brand(p.getBrand())
                        .description(p.getDescription())
                        .images(p.getImages().stream()
                            .map(image -> ImageDTO.builder()
                                .id(image.getId())
                                .name(image.getName())
                                .type(image.getType())
                                .picByte(this.imageService.decompressBytes(image.getPicByte()))
                                .build()).collect(Collectors.toSet()))
                        .promotions(p.getPromotions().stream().map(this.promotionMapper::mapToDto).collect(Collectors.toSet()))
                        .build())
                    .categories(p.getCategories().stream().map(this.categoryMapper::mapToDto).collect(Collectors.toSet()))
                    .promotions(p.getPromotions().stream().map(this.promotionMapper::mapToDto).collect(Collectors.toSet()))
                    .build());
            }

        }
        return offersDTO;
    }

    @Override
    @Transactional
    public OfferDTO findById(Long id) {
        final Offer foundOffer = this.offerRepository.findById(id)
            .orElseThrow(ResourceNotFoundException::new);

        return OfferDTO.builder()
            .id(foundOffer.getId())
            .endDate(foundOffer.getEndDate())
            .quantity(foundOffer.getQuantity())
            .creationDate(foundOffer.getCreationDate())
            .product(ProductDTO.builder()
                .id(foundOffer.getProduct().getId())
                .name(foundOffer.getProduct().getName())
                .price(foundOffer.getProduct().getPrice())
                .model(foundOffer.getProduct().getModel())
                .brand(foundOffer.getProduct().getBrand())
                .description(foundOffer.getProduct().getDescription())
                .images(foundOffer.getProduct().getImages().stream()
                    .map(image -> ImageDTO.builder()
                        .id(image.getId())
                        .name(image.getName())
                        .type(image.getType())
                        .picByte(this.imageService.decompressBytes(image.getPicByte()))
                        .build()).collect(Collectors.toSet()))
                .reviews(foundOffer.getProduct().getReviews().stream()
                    .map(review -> ReviewDTO.builder()
                        .id(review.getId())
                        .plusRate(review.getPlusRate())
                        .minusRate(review.getMinusRate())
                        .creationDate(review.getCreationDate())
                        .reviewComment(review.getReviewComment())
                        .usernameDTO(UsernameDTO.builder()
                            .id(review.getUser().getId())
                            .username(review.getUser().getUsername()).build())
                        .build()).collect(Collectors.toSet()))
                .promotions(foundOffer.getProduct().getPromotions().stream().map(this.promotionMapper::mapToDto).collect(Collectors.toSet()))
                .build())
            .categories(foundOffer.getProduct().getCategories().stream().map(this.categoryMapper::mapToDto).collect(Collectors.toSet()))
            .build();
    }

    @Override
    public void deleteOfferById(Long offerId) {
        this.offerValidator.validateExistModelById(offerId);
        this.offerRepository.delete(entityManager.getReference(Offer.class, offerId));
    }
}
