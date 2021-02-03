package pl.dmuszynski.libso.service;

import pl.dmuszynski.libso.model.Offer;
import pl.dmuszynski.libso.payload.dto.OfferDTO;
import pl.dmuszynski.libso.payload.dto.PurchaseDTO;

import java.util.Set;

public interface OfferService {
    OfferDTO createOffer(OfferDTO offerDetails);
    OfferDTO updateOfferById(OfferDTO offerDetails, Long offerId);
    Set<OfferDTO> findAllOfferDTO();
    OfferDTO findById(Long id);
    Offer updatePurchaseOffer(PurchaseDTO purchaseDetails);
    void deleteOfferById(Long offerId);
}
