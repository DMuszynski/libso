package pl.dmuszynski.libso.service.implementation;

import pl.dmuszynski.libso.repository.OfferRepository;
import pl.dmuszynski.libso.validator.OfferValidator;
import pl.dmuszynski.libso.service.OfferService;
import pl.dmuszynski.libso.payload.dto.OfferDTO;
import pl.dmuszynski.libso.mapper.OfferMapper;
import pl.dmuszynski.libso.model.Product;
import pl.dmuszynski.libso.model.Offer;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

@Transactional
@RequiredArgsConstructor
@Service(value = "offerService")
public class OfferServiceImpl implements OfferService {

    private final OfferRepository offerRepository;
    private final OfferValidator offerValidator;
    private final OfferMapper offerMapper;
    private final EntityManager entityManager;

    @Override
    public OfferDTO createOffer(OfferDTO offerDetails) {
        final Offer createdOffer = this.offerRepository.save(
            Offer.builder()
                .quantity(offerDetails.getQuantity())
                .endDate(offerDetails.getEndDate())
                .creationDate(offerDetails.getCreationDate())
                .product(this.entityManager.getReference(Product.class, offerDetails.getProduct().getId()))
                .build()
        );

        return this.offerMapper.mapToDto(createdOffer);
    }

    @Override
    public OfferDTO updateOfferById(OfferDTO offerDetails, Long offerId) {
        this.offerValidator.validateModelAndModelDtoIds(offerId, offerDetails.getId());
        final Offer updatedOffer = this.offerRepository.save(
            Offer.builder()
                .id(offerId)
                .quantity(offerDetails.getQuantity())
                .endDate(offerDetails.getEndDate())
                .creationDate(offerDetails.getCreationDate())
                .product(this.entityManager.getReference(Product.class, offerDetails.getProduct().getId()))
                .build()
        );

        return this.offerMapper.mapToDto(updatedOffer);
    }

    @Override
    public void deleteOfferById(Long offerId) {
        this.offerValidator.validateExistModelById(offerId);
        this.entityManager.remove(entityManager.getReference(Offer.class, offerId));
    }
}
