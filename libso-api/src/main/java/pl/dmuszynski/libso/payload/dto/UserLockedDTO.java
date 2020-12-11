package pl.dmuszynski.libso.payload.dto;

import pl.dmuszynski.libso.payload.UserLockedView;
import lombok.experimental.SuperBuilder;
import lombok.NoArgsConstructor;

@SuperBuilder
@NoArgsConstructor
public class UserLockedDTO extends AbstractDTO implements UserLockedView {

    private boolean locked;

    @Override
    public boolean isLocked() {
        return locked;
    }
}
