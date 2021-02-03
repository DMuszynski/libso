package pl.dmuszynski.libso.api;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import pl.dmuszynski.libso.payload.dto.PurchaseDTO;
import pl.dmuszynski.libso.service.PurchaseService;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(value = "libso/purchases")
public class PurchaseController {

    private final PurchaseService purchaseService;

    @PostMapping
    @PreAuthorize(value = "hasRole('USER')")
    public ResponseEntity<PurchaseDTO> createPurchase(@RequestBody PurchaseDTO purchaseDetails) {
        final PurchaseDTO createdPurchase =  this.purchaseService.createPurchase(purchaseDetails);
        return new ResponseEntity<>(createdPurchase, HttpStatus.OK);
    }

    @PatchMapping(value = "/{purchaseId}/products-amount")
    @PreAuthorize(value = "hasRole('USER')")
    public ResponseEntity<HttpStatus> updatePurchaseProductsAmount(@RequestBody PurchaseDTO purchaseDetails, @PathVariable Long purchaseId) {
        this.purchaseService.updatePurchaseProductAmount(purchaseDetails.getProductsAmount(), purchaseId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping(value = "/{purchaseId}")
    @PreAuthorize(value = "hasRole('USER')")
    public ResponseEntity<HttpStatus> deletePurchase(@PathVariable Long purchaseId) {
        this.purchaseService.deletePurchase(purchaseId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
