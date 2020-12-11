package pl.dmuszynski.libso.service;

import pl.dmuszynski.libso.payload.dto.AddressDTO;
import pl.dmuszynski.libso.payload.AddressView;

import java.util.Set;

public interface AddressService {
    AddressDTO createUserAddress(AddressDTO userAddressDetails, Long userId);
    AddressDTO updateUserAddressById(AddressDTO userAddressDetails, Long userId, Long addressId);
    Set<AddressView> findAllAddressesViewByUserId(Long userId);
    void deleteUserAddressById(Long userId, Long addressId);
}
