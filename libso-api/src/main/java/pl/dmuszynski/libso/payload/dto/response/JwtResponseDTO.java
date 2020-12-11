package pl.dmuszynski.libso.payload.dto.response;

import lombok.Getter;
import pl.dmuszynski.libso.payload.dto.AbstractDTO;
import lombok.experimental.SuperBuilder;
import lombok.NoArgsConstructor;

import java.util.Set;

@Getter
@SuperBuilder
@NoArgsConstructor
public class JwtResponseDTO extends AbstractDTO {
    private String email;
    private String username;
    private String accessToken;
    private final String type = "Bearer";
    private Set<String> authorities;
}
