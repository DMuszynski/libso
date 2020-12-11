package pl.dmuszynski.libso.mapper;

import pl.dmuszynski.libso.payload.dto.PromotionDTO;
import pl.dmuszynski.libso.model.Promotion;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PromotionMapper {
    PromotionDTO mapToDto(Promotion mappedPromotion);
    Promotion mapToModel(PromotionDTO mappedPromotionDto);
}
