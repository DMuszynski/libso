package pl.dmuszynski.libso.payload.dto.request;

import lombok.Getter;

@Getter
public class SignInRequestDTO {
    private String username;
    private String password;
}
