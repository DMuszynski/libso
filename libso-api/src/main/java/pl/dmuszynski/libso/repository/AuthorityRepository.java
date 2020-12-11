package pl.dmuszynski.libso.repository;

import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.jpa.repository.JpaRepository;
import pl.dmuszynski.libso.model.AuthorityType;
import pl.dmuszynski.libso.model.Authority;

import java.util.Optional;

@RepositoryRestResource(collectionResourceRel = "authorities", path = "authorities")
public interface AuthorityRepository extends JpaRepository<Authority, Long> {
    Optional<Authority> findByAuthorityType(AuthorityType authorityType);
}
