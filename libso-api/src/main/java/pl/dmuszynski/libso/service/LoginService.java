package pl.dmuszynski.libso.service;

import pl.dmuszynski.libso.payload.dto.response.JwtResponseDTO;

public interface LoginService {
    JwtResponseDTO signIn(String username, String password);
}
