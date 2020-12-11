package pl.dmuszynski.libso.repository;

import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.jpa.repository.JpaRepository;
import pl.dmuszynski.libso.model.Address;
import pl.dmuszynski.libso.payload.AddressView;

import java.util.List;

@RepositoryRestResource(collectionResourceRel = "addresses", path = "addresses")
public interface AddressRepository extends JpaRepository<Address, Long> {
    List<AddressView> findAllAddressesViewByUserId(Long userId);
}
