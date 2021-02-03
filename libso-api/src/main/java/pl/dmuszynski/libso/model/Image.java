package pl.dmuszynski.libso.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;

@Entity
@Getter
@SuperBuilder
@NoArgsConstructor
public class Image extends AbstractEntity {

    private String name;

    private String type;

    private byte[] picByte;

    @ManyToOne(fetch = FetchType.LAZY)
    private Product product;
}
