package pl.dmuszynski.libso.repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.jpa.repository.JpaRepository;
import pl.dmuszynski.libso.model.Address;
import pl.dmuszynski.libso.payload.AddressView;

import java.util.List;

@RepositoryRestResource(collectionResourceRel = "addresses", path = "addresses")
public interface AddressRepository extends JpaRepository<Address, Long> {
    List<AddressView> findAllAddressesViewByUserId(Long userId);
    Address findAddressByDeliveryAddressTrueAndUserId(Long userId);

    @Modifying
    @Query(value = "UPDATE Address a SET a.deliveryAddress = :deliveryAddress WHERE a.id = :addressId")
    void updateDeliveryAddressByAddressId(@Param("deliveryAddress") boolean deliveryAddress, @Param("addressId") Long addressId);
}
