package pl.dmuszynski.libso.payload.dto.request;

import lombok.Getter;

@Getter
public class SignUpRequestDTO {
    private String email;
    private String username;
    private String password;
}
