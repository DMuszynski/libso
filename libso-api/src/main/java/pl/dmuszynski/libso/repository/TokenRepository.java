package pl.dmuszynski.libso.repository;

import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.repository.CrudRepository;
import pl.dmuszynski.libso.model.Token;

import java.util.Optional;

@RepositoryRestResource(collectionResourceRel = "tokens", path = "tokens")
public interface TokenRepository extends CrudRepository<Token, Long> {
    Optional<Token> findByValue(String value);
    boolean existsTokenByValue(String value);
}
