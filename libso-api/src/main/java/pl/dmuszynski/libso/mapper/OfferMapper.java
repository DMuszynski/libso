package pl.dmuszynski.libso.mapper;

import pl.dmuszynski.libso.payload.dto.OfferDTO;
import pl.dmuszynski.libso.model.Offer;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface OfferMapper {
    OfferDTO mapToDto(Offer mappedOffer);
    Offer mapToModel(OfferDTO mappedOfferDTO);
}
