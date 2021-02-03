package pl.dmuszynski.libso.service;

public interface RegistrationService {
    void signUp(String email, String username, String password);
    String activateAccountByUserToken(String value);
}

