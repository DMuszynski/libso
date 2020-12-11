package pl.dmuszynski.libso.payload.dto;

import pl.dmuszynski.libso.payload.UserPasswordView;
import lombok.experimental.SuperBuilder;
import lombok.NoArgsConstructor;

@SuperBuilder
@NoArgsConstructor
public class UserPasswordDTO extends AbstractDTO implements UserPasswordView {

    private String password;

    @Override
    public String getPassword() {
        return password;
    }
}
