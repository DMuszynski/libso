package pl.dmuszynski.libso.payload.dto;

import pl.dmuszynski.libso.payload.UserEmailView;
import lombok.experimental.SuperBuilder;
import lombok.NoArgsConstructor;

@SuperBuilder
@NoArgsConstructor
public class UserEmailDTO extends AbstractDTO implements UserEmailView {

    private String email;

    @Override
    public String getEmail() {
        return email;
    }
}
