package pl.dmuszynski.libso.model;

import lombok.AllArgsConstructor;
import org.springframework.data.annotation.Immutable;
import lombok.NoArgsConstructor;
import lombok.Getter;

import javax.persistence.FetchType;
import javax.persistence.OneToOne;
import javax.persistence.Entity;
import javax.persistence.MapsId;

@Entity
@Getter
@Immutable
@NoArgsConstructor
@AllArgsConstructor
public class Token extends AbstractEntity {

    private String value;

    @MapsId
    @OneToOne(fetch = FetchType.LAZY)
    private User user;
}
