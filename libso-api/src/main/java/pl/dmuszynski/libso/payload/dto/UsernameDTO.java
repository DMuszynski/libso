package pl.dmuszynski.libso.payload.dto;

import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import pl.dmuszynski.libso.payload.UsernameView;

@SuperBuilder
@NoArgsConstructor
public class UsernameDTO extends AbstractDTO implements UsernameView {

    private String username;

    @Override
    public String getUsername() {
        return username;
    }
}
