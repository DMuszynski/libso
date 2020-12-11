package pl.dmuszynski.libso.payload.dto;

import pl.dmuszynski.libso.payload.EntityView;
import lombok.experimental.SuperBuilder;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@SuperBuilder
@NoArgsConstructor
public abstract class AbstractDTO implements EntityView, Serializable {

    private Long id;

    @Override
    public Long getId() {
        return id;
    }
}
