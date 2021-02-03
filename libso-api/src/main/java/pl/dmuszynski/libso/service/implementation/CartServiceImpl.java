package pl.dmuszynski.libso.service.implementation;

import lombok.RequiredArgsConstructor;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import pl.dmuszynski.libso.mapper.CategoryMapper;
import pl.dmuszynski.libso.mapper.PromotionMapper;
import pl.dmuszynski.libso.model.Cart;
import pl.dmuszynski.libso.model.User;
import pl.dmuszynski.libso.payload.dto.*;
import pl.dmuszynski.libso.repository.CartRepository;
import pl.dmuszynski.libso.repository.ProductRepository;
import pl.dmuszynski.libso.service.CartService;
import pl.dmuszynski.libso.service.ImageService;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service(value = "cartService")
public class CartServiceImpl implements CartService {

    private final CartRepository cartRepository;
    private final PromotionMapper promotionMapper;
    private final CategoryMapper categoryMapper;
    private final EntityManager entityManager;
    private final ProductRepository productRepository;
    private final ImageService imageService;

    @Override
    @Transactional
    public Cart clearCart(Long cartId) {
        final Cart foundCartToClear = this.cartRepository.findById(cartId)
            .orElseThrow(ResourceNotFoundException::new);
        foundCartToClear.setCapacity(0);
        foundCartToClear.setTotalPrice(BigDecimal.valueOf(0));

        return foundCartToClear;
    }

    @Override
    @Transactional
    public void updateCart(CartDTO cartDetails, Long cartId) {
        final Cart foundCart = this.cartRepository.findById(cartId).orElseThrow();
        foundCart.setTotalPrice(cartDetails.getTotalPrice());
        foundCart.setCapacity(cartDetails.getCapacity());
    }

    @Override
    public void createCart(User user) {
        this.cartRepository.save(Cart.builder()
            .user(user)
            .capacity(0)
            .totalPrice(BigDecimal.valueOf(0.0))
            .build());
    }

    @Override
    @Transactional
    public CartDTO findCartDtoByUserId(Long userId) {
        final Cart foundCart = this.cartRepository.findByUserId(userId);
        return CartDTO.builder()
            .id(foundCart.getId())
            .capacity(foundCart.getCapacity())
            .totalPrice(foundCart.getTotalPrice())
            .purchases(foundCart.getPurchases().stream()
                .map(purchase -> PurchaseDTO.builder()
                    .id(purchase.getId())
                    .cartId(foundCart.getId())
                    .productsAmount(purchase.getProductsAmount())
                    .offer(OfferDTO.builder()
                        .id(purchase.getOffer().getId())
                        .quantity(purchase.getOffer().getQuantity())
                        .creationDate(purchase.getOffer().getCreationDate())
                        .endDate(purchase.getOffer().getEndDate())
                        .product(ProductDTO.builder()
                            .id(purchase.getOffer().getProduct().getId())
                            .name(purchase.getOffer().getProduct().getName())
                            .description(purchase.getOffer().getProduct().getDescription())
                            .price(purchase.getOffer().getProduct().getPrice())
                            .model(purchase.getOffer().getProduct().getModel())
                            .brand(purchase.getOffer().getProduct().getBrand())
                            .promotions(purchase.getOffer().getProduct().getPromotions().stream()
                                .map(this.promotionMapper::mapToDto).collect(Collectors.toSet()))
                            .images(purchase.getOffer().getProduct().getImages().stream()
                                .map(image -> ImageDTO.builder()
                                    .id(image.getId())
                                    .name(image.getName())
                                    .type(image.getType())
                                    .picByte(this.imageService.decompressBytes(image.getPicByte()))
                                    .build()).collect(Collectors.toSet()))
                            .build())
                        .build())
                    .build())
                .collect(Collectors.toSet()))
            .build();
    }
}
