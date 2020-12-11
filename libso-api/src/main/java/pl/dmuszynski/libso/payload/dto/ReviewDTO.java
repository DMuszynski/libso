package pl.dmuszynski.libso.payload.dto;

import pl.dmuszynski.libso.payload.ReviewView;
import lombok.experimental.SuperBuilder;
import lombok.NoArgsConstructor;

@SuperBuilder
@NoArgsConstructor
public class ReviewDTO extends AbstractDTO implements ReviewView {

    private int grade;

    private String reviewComment;

    @Override
    public int getGrade() {
        return grade;
    }

    @Override
    public String getReviewComment() {
        return reviewComment;
    }
}
