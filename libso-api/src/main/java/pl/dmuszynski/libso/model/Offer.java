package pl.dmuszynski.libso.model;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.data.annotation.CreatedDate;
import lombok.experimental.SuperBuilder;
import lombok.NoArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import javax.persistence.*;

@Entity
@Getter
@SuperBuilder
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class Offer extends AbstractEntity {

    private int quantity;

    private LocalDate endDate;

    @CreatedDate
    private LocalDateTime creationDate;

    @MapsId
    @OneToOne(fetch = FetchType.LAZY)
    private Product product;
}
