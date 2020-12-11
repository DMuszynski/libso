package pl.dmuszynski.libso.api;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import pl.dmuszynski.libso.service.PromotionService;
import pl.dmuszynski.libso.payload.dto.PromotionDTO;
import pl.dmuszynski.libso.payload.PromotionView;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@PreAuthorize(value = "hasRole('USER')")
@RequestMapping(value = "libso/products/{productId}/promotions")
public class PromotionController {

    private final PromotionService promotionService;

    @PostMapping
    public ResponseEntity<PromotionView> createProductPromotion(@RequestBody PromotionDTO productPromotionDetails, @PathVariable Long productId) {
        final PromotionDTO createdProductPromotionDto = this.promotionService.createProductPromotion(productPromotionDetails, productId);
        return new ResponseEntity<>(createdProductPromotionDto, HttpStatus.CREATED);
    }

    @PutMapping(value = "/{promotionId}")
    public ResponseEntity<PromotionView> updateProductPromotionById(@RequestBody PromotionDTO productPromotionDetails, @PathVariable Long productId, @PathVariable Long promotionId) {
        final PromotionDTO updatedProductPromotionDto = this.promotionService.updateProductPromotionById(productPromotionDetails, productId, promotionId);
        return new ResponseEntity<>(updatedProductPromotionDto, HttpStatus.OK);
    }


    @DeleteMapping(value = "/{promotionId}")
    public ResponseEntity<HttpStatus> deleteProductPromotionById(@PathVariable Long productId, @PathVariable Long promotionId) {
        this.promotionService.deleteProductPromotionById(productId, promotionId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
