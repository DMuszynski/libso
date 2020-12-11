package pl.dmuszynski.libso.service;

import pl.dmuszynski.libso.payload.dto.PromotionDTO;

public interface PromotionService {
    PromotionDTO createProductPromotion(PromotionDTO productPromotionDetails, Long productId);
    PromotionDTO updateProductPromotionById(PromotionDTO productPromotionDetails, Long productId, Long promotionId);
    void deleteProductPromotionById(Long productId, Long promotionId);
}
