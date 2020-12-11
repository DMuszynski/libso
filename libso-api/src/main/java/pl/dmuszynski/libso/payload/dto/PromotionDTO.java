package pl.dmuszynski.libso.payload.dto;

import pl.dmuszynski.libso.payload.PromotionView;
import lombok.experimental.SuperBuilder;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@SuperBuilder
@NoArgsConstructor
public class PromotionDTO extends AbstractDTO implements PromotionView {

    private int percentValue;

    private LocalDate startDate;

    private LocalDate endDate;

    @Override
    public int getPercentValue() {
        return this.percentValue;
    }

    @Override
    public LocalDate getStartDate() {
        return this.startDate;
    }

    @Override
    public LocalDate getEndDate() {
        return this.endDate;
    }
}
