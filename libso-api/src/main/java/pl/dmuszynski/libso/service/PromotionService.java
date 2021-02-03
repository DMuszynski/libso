package pl.dmuszynski.libso.service;

import pl.dmuszynski.libso.payload.PromotionView;
import pl.dmuszynski.libso.payload.dto.PromotionDTO;

import java.util.Set;

public interface PromotionService {
    PromotionDTO createPromotion(PromotionDTO promotionDetails);
    PromotionDTO updatePromotionById(PromotionDTO promotionDetails, Long promotionId);
    Set<PromotionView> findAllPromotionView();
    void deletePromotionById(Long promotionId);
}
