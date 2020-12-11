package pl.dmuszynski.libso.service;

import pl.dmuszynski.libso.model.Token;
import pl.dmuszynski.libso.model.User;

public interface TokenService {
    Token findByValue(String value);
    void sendToken(User user);
}
