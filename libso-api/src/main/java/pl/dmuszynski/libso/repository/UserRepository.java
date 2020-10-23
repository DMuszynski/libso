package pl.dmuszynski.libso.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.dmuszynski.libso.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

}
