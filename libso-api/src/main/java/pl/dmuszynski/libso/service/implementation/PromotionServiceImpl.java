package pl.dmuszynski.libso.service.implementation;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import pl.dmuszynski.libso.mapper.PromotionMapper;
import pl.dmuszynski.libso.payload.PromotionView;
import pl.dmuszynski.libso.repository.PromotionRepository;
import pl.dmuszynski.libso.validator.PromotionValidator;
import pl.dmuszynski.libso.payload.dto.PromotionDTO;
import pl.dmuszynski.libso.service.PromotionService;
import pl.dmuszynski.libso.model.Promotion;
import lombok.RequiredArgsConstructor;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.Set;
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
    private final EntityManager entityManager;

    @Override
    public PromotionDTO createPromotion(PromotionDTO promotionDetails) {
        final Promotion createdProductPromotion = this.promotionRepository.save(
            Promotion.builder()
                .name(promotionDetails.getName())
                .percentValue(promotionDetails.getPercentValue())
                .startDate(promotionDetails.getStartDate())
                .endDate(promotionDetails.getEndDate())
                .build()
        );

        return this.promotionMapper.mapToDto(createdProductPromotion);
    }

    @Override
    public PromotionDTO updatePromotionById(PromotionDTO promotionDetails, Long promotionId) {
        this.promotionValidator.validateModelAndModelDtoIds(promotionId, promotionDetails.getId());
        final Promotion updatedProductPromotion = this.promotionRepository.save(
            Promotion.builder()
                .id(promotionDetails.getId())
                .name(promotionDetails.getName())
                .percentValue(promotionDetails.getPercentValue())
                .startDate(promotionDetails.getStartDate())
                .endDate(promotionDetails.getEndDate())
                .build()
        );

        return this.promotionMapper.mapToDto(updatedProductPromotion);
    }

    @Override
    public Set<PromotionView> findAllPromotionView() {
        return this.promotionRepository.findAll().stream()
            .map(this.promotionMapper::mapToDto)
            .collect(Collectors.toSet());
    }

    @Override
    public void deletePromotionById(Long promotionId) {
        this.promotionValidator.validateExistModelById(promotionId);
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
