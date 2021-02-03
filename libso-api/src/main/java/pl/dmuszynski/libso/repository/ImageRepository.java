package pl.dmuszynski.libso.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import pl.dmuszynski.libso.model.Image;

@RepositoryRestResource(collectionResourceRel = "images", path = "images")
public interface ImageRepository extends JpaRepository<Image, Long> {
}
