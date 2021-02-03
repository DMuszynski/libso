package pl.dmuszynski.libso.payload.dto;

import pl.dmuszynski.libso.payload.ReviewView;
import lombok.experimental.SuperBuilder;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@SuperBuilder
@NoArgsConstructor
public class ReviewDTO extends AbstractDTO implements ReviewView {

    private int plusRate;

    private int minusRate;

    private String reviewComment;

    private LocalDateTime creationDate;

    private UsernameDTO usernameDTO;

    @Override
    public int getPlusRate() {
        return this.plusRate;
    }

    @Override
    public int getMinusRate() {
        return this.minusRate;
    }

    @Override
    public String getReviewComment() {
        return reviewComment;
    }

    @Override
    public LocalDateTime getCreationDate() {
        return this.creationDate;
    }

    public UsernameDTO getUsernameDTO() {
        return this.usernameDTO;
    }
}
