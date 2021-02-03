package pl.dmuszynski.libso.model;

import lombok.experimental.SuperBuilder;
import lombok.NoArgsConstructor;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@SuperBuilder
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class Review extends AbstractEntity {

    private int plusRate;

    private int minusRate;

    private String reviewComment;

    @CreatedDate
    private LocalDateTime creationDate;

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    private Product product;

    public void setPlusRate(int plusRate) {
        this.plusRate = plusRate;
    }

    public void setMinusRate(int minusRate) {
        this.minusRate = minusRate;
    }
}
