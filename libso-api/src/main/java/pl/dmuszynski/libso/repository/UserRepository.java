package pl.dmuszynski.libso.repository;

import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.userdetails.UserDetails;
import pl.dmuszynski.libso.payload.UserPasswordView;
import pl.dmuszynski.libso.model.User;

import java.util.Optional;

@RepositoryRestResource(collectionResourceRel = "users", path = "users")
public interface UserRepository extends JpaRepository<User, Long> {

    boolean existsByEmail(String email);

    boolean existsByUsername(String username);

    Optional<UserDetails> findByUsername(String username);

    Optional<UserPasswordView> findUserPasswordById(Long userId);

    @Modifying
    @Query(value = "UPDATE User u SET u.email = :email WHERE u.id = :id")
    void updateEmailById(@Param("email") String email, @Param("id") Long id);

    @Modifying
    @Query(value = "UPDATE User u SET u.password = :password WHERE u.id = :id")
    void updatePasswordById(@Param("password") String password, @Param("id") Long id);

    @Modifying
    @Query(value = "UPDATE User u SET u.enabled = true WHERE u.id = :id")
    void activateAccount(@Param("id") Long id);
}
