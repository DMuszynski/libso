package pl.dmuszynski.libso.service;

import pl.dmuszynski.libso.payload.dto.OfferDTO;

public interface OfferService {
    OfferDTO createOffer(OfferDTO offerDetails);
    OfferDTO updateOfferById(OfferDTO offerDetails, Long offerId);
    void deleteOfferById(Long offerId);
}
