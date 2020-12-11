package pl.dmuszynski.libso.service.implementation;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import pl.dmuszynski.libso.repository.PromotionRepository;
import pl.dmuszynski.libso.validator.PromotionValidator;
import pl.dmuszynski.libso.validator.ProductValidator;
import pl.dmuszynski.libso.payload.dto.PromotionDTO;
import pl.dmuszynski.libso.service.PromotionService;
import pl.dmuszynski.libso.mapper.PromotionMapper;
import pl.dmuszynski.libso.model.Promotion;
import pl.dmuszynski.libso.model.Product;
import lombok.RequiredArgsConstructor;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.stream.Collectors;
import java.util.List;
import java.time.LocalDate;

@Transactional
@RequiredArgsConstructor
@Service(value = "promotionService")
public class PromotionServiceImpl implements PromotionService {

    private final PromotionRepository promotionRepository;
    private final PromotionValidator promotionValidator;
    private final PromotionMapper promotionMapper;
    private final ProductValidator productValidator;
    private final EntityManager entityManager;

    @Override
    public PromotionDTO createProductPromotion(PromotionDTO productPromotionDetails, Long productId) {
        this.productValidator.validateExistModelById(productId);
        final Promotion createdProductPromotion = this.promotionRepository.save(
            Promotion.builder()
                .percentValue(productPromotionDetails.getPercentValue())
                .startDate(productPromotionDetails.getStartDate())
                .endDate(productPromotionDetails.getEndDate())
                .product(this.entityManager
                    .getReference(Product.class, productId))
                .build()
        );

        return this.promotionMapper.mapToDto(createdProductPromotion);
    }

    @Override
    public PromotionDTO updateProductPromotionById(PromotionDTO productPromotionDetails, Long productId, Long promotionId) {
        this.promotionValidator.validateProductPromotionIds(promotionId, productPromotionDetails.getId(), productId);
        final Promotion updatedProductPromotion = this.promotionRepository.save(
            Promotion.builder()
                .id(productPromotionDetails.getId())
                .percentValue(productPromotionDetails.getPercentValue())
                .startDate(productPromotionDetails.getStartDate())
                .endDate(productPromotionDetails.getEndDate())
                .product(this.entityManager
                    .getReference(Product.class, productId))
                .build()
        );

        return this.promotionMapper.mapToDto(updatedProductPromotion);
    }

    @Override
    public void deleteProductPromotionById(Long productId, Long promotionId) {
        this.promotionValidator.validateProductPromotionIds(promotionId, productId);
        this.entityManager.remove(this.entityManager.getReference(Promotion.class, promotionId));
    }

    @Scheduled(cron = "0 0 0 * * *", zone = "GMT+2")
    public void deleteCompletedPromotions() {
        final List<Promotion> completedProductPromotions = this.promotionRepository.findAll().stream()
            .filter(promotion -> promotion.getEndDate().isAfter(LocalDate.now()))
            .collect(Collectors.toList());

        this.promotionRepository.deleteAll(completedProductPromotions);
    }
}
