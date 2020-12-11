package pl.dmuszynski.libso.service;

import pl.dmuszynski.libso.model.AuthorityType;
import pl.dmuszynski.libso.model.Authority;

public interface AuthorityService {
    Authority findByAuthorityType(AuthorityType authorityType);
}
