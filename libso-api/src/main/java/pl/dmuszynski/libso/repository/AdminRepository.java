package pl.dmuszynski.libso.repository;

import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import pl.dmuszynski.libso.payload.AuthorizedUserView;
import pl.dmuszynski.libso.model.User;

import java.util.List;

@RepositoryRestResource(collectionResourceRel = "users", path = "users", excerptProjection = AuthorizedUserView.class)
public interface AdminRepository extends JpaRepository<User, Long> {

    List<AuthorizedUserView> findAllAuthorizedUserViewBy();

    @Modifying
    @Query(value = "UPDATE User u SET u.locked = :locked WHERE u.id = :userId")
    void updateUserLockedById(@Param("locked") boolean locked, @Param("userId") Long userId);
}
