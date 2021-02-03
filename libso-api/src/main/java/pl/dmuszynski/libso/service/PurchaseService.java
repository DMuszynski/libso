package pl.dmuszynski.libso.service;

import pl.dmuszynski.libso.payload.dto.PurchaseDTO;

public interface PurchaseService {
    PurchaseDTO createPurchase(PurchaseDTO purchaseDetails);
    void updatePurchaseProductAmount(int productAmount, Long purchaseId);
    void deleteAllByCartId(Long cartId);
    void deletePurchase(Long purchaseId);
}
