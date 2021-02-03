package pl.dmuszynski.libso.api;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import pl.dmuszynski.libso.service.PromotionService;
import pl.dmuszynski.libso.payload.dto.PromotionDTO;
import pl.dmuszynski.libso.payload.PromotionView;
import lombok.RequiredArgsConstructor;

import java.util.Set;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(value = "libso/promotions")
public class PromotionController {

    private final PromotionService promotionService;

    @PostMapping
    @PreAuthorize(value = "hasRole('MODERATOR')")
    public ResponseEntity<PromotionView> createPromotion(@RequestBody PromotionDTO promotionDetails) {
        final PromotionDTO createdPromotionDto = this.promotionService.createPromotion(promotionDetails);
        return new ResponseEntity<>(createdPromotionDto, HttpStatus.CREATED);
    }

    @PutMapping(value = "/{promotionId}")
    @PreAuthorize(value = "hasRole('MODERATOR')")
    public ResponseEntity<PromotionView> updatePromotionById(@RequestBody PromotionDTO promotionDetails, @PathVariable Long promotionId) {
        final PromotionDTO updatedPromotionDto = this.promotionService.updatePromotionById(promotionDetails, promotionId);
        return new ResponseEntity<>(updatedPromotionDto, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<Set<PromotionView>> findAllPromotionView() {
        final Set<PromotionView> foundAllPromotionViewSet = this.promotionService.findAllPromotionView();

        if(!foundAllPromotionViewSet.isEmpty())
            return new ResponseEntity<>(foundAllPromotionViewSet, HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping(value = "/{promotionId}")
    @PreAuthorize(value = "hasRole('MODERATOR')")
    public ResponseEntity<HttpStatus> deletePromotionById(@PathVariable Long promotionId) {
        this.promotionService.deletePromotionById(promotionId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
