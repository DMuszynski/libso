package pl.dmuszynski.libso.service.implementation;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.dmuszynski.libso.model.Cart;
import pl.dmuszynski.libso.model.Offer;
import pl.dmuszynski.libso.model.Purchase;
import pl.dmuszynski.libso.payload.dto.PurchaseDTO;
import pl.dmuszynski.libso.repository.PurchaseRepository;
import pl.dmuszynski.libso.service.PurchaseService;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

@RequiredArgsConstructor
@Service(value = "purchaseService")
public class PurchaseServiceImpl implements PurchaseService {

    private final PurchaseRepository purchaseRepository;
    private final EntityManager entityManager;

    @Override
    @Transactional
    public PurchaseDTO createPurchase(PurchaseDTO purchaseDetails) {
        final Purchase foundPurchase = this.purchaseRepository.findByOfferIdAndCartId(purchaseDetails.getOffer().getId(),
            purchaseDetails.getCartId());
        if (foundPurchase == null) {
            this.purchaseRepository.save(Purchase.builder()
                .cart(this.entityManager.getReference(Cart.class, purchaseDetails.getCartId()))
                .offer(this.entityManager.getReference(Offer.class, purchaseDetails.getOffer().getId()))
                .productsAmount(purchaseDetails.getProductsAmount())
                .build());
        } else  {
            foundPurchase.setProductsAmount(Math.min((foundPurchase.getProductsAmount() + purchaseDetails.getProductsAmount()), foundPurchase.getOffer().getQuantity()));
        }

        return purchaseDetails;
    }

    @Override
    @Transactional
    public void updatePurchaseProductAmount(int productsAmount, Long purchaseId) {
        final Purchase updatedPurchase = this.purchaseRepository.findById(purchaseId).orElseThrow();
        updatedPurchase.setProductsAmount(productsAmount);
    }

    @Override
    public void deleteAllByCartId(Long cartId) {
        this.purchaseRepository.deleteAllByCartId(cartId);
    }

    @Override
    @Transactional
    public void deletePurchase(Long purchaseId) {
        this.purchaseRepository.delete(this.entityManager.getReference(Purchase.class, purchaseId));
    }
}
