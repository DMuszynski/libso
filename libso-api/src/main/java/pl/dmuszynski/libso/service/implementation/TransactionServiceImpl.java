package pl.dmuszynski.libso.service.implementation;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.dmuszynski.libso.mapper.PromotionMapper;
import pl.dmuszynski.libso.model.Transaction;
import pl.dmuszynski.libso.model.User;
import pl.dmuszynski.libso.payload.dto.*;
import pl.dmuszynski.libso.repository.TransactionRepository;
import pl.dmuszynski.libso.service.ImageService;
import pl.dmuszynski.libso.service.TransactionService;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Set;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service(value = "transactionService")
public class TransactionServiceImpl implements TransactionService {

    private final ImageService imageService;
    private final EntityManager entityManager;
    private final PromotionMapper promotionMapper;
    private final TransactionRepository transactionRepository;

    @Override
    public Transaction createTransaction(Long userId, BigDecimal totalPrice, int transactionProductsAmount) {
        return this.transactionRepository.save(Transaction.builder()
            .user(this.entityManager.getReference(User.class, userId))
            .transactionProductsAmount(transactionProductsAmount)
            .transactionDate(LocalDateTime.now())
            .totalPrice(totalPrice)
            .build());
    }

    @Override
    @Transactional
    public Set<TransactionDTO> findAllTransactionByUserId(Long userId) {
        return this.transactionRepository.findAllByUserId(userId).stream()
            .map(transaction -> TransactionDTO.builder()
                .id(transaction.getId())
                .userId(transaction.getUser().getId())
                .totalPrice(transaction.getTotalPrice())
                .transactionDate(transaction.getTransactionDate())
                .transactionProductsAmount(transaction.getTransactionProductsAmount())
                .products(transaction.getProducts().stream()
                    .map(product -> ProductDTO.builder()
                        .id(product.getId())
                        .name(product.getName())
                        .price(product.getPrice())
                        .model(product.getModel())
                        .brand(product.getBrand())
                        .description(product.getDescription())
                        .promotions(product.getPromotions().stream()
                            .map(this.promotionMapper::mapToDto).collect(Collectors.toSet()))
                        .reviews(product.getReviews().stream()
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
                        .images(product.getImages().stream()
                            .map(image -> ImageDTO.builder()
                                .id(image.getId())
                                .name(image.getName())
                                .type(image.getType())
                                .picByte(this.imageService.decompressBytes(image.getPicByte()))
                                .build()).collect(Collectors.toSet()))
                        .build())
                    .collect(Collectors.toSet()))
                .build())
            .collect(Collectors.toSet());
    }
}
