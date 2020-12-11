package pl.dmuszynski.libso.mapper;

import pl.dmuszynski.libso.payload.dto.ReviewDTO;
import pl.dmuszynski.libso.model.Review;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ReviewMapper {
    ReviewDTO mapToDto(Review mappedReview);
    Review mapToModel(ReviewDTO mappedReviewDto);
}