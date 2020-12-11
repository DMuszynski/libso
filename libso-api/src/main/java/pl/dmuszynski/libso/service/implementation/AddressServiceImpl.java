package pl.dmuszynski.libso.service.implementation;

import pl.dmuszynski.libso.mapper.AddressMapper;
import pl.dmuszynski.libso.repository.AddressRepository;
import pl.dmuszynski.libso.validator.AddressValidator;
import pl.dmuszynski.libso.validator.UserValidator;
import pl.dmuszynski.libso.service.AddressService;
import pl.dmuszynski.libso.payload.dto.AddressDTO;
import pl.dmuszynski.libso.payload.AddressView;
import pl.dmuszynski.libso.model.Address;
import pl.dmuszynski.libso.model.User;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.Set;

@RequiredArgsConstructor
@Service(value = "addressService")
public class AddressServiceImpl implements AddressService {

    private final AddressRepository addressRepository;
    private final AddressValidator addressValidator;
    private final UserValidator userValidator;
    private final AddressMapper addressMapper;
    private final EntityManager entityManager;

    @Override
    @Transactional
    public AddressDTO createUserAddress(AddressDTO userAddressDetails, Long userId) {
        this.userValidator.validateExistModelById(userId);
        final Address createdUserAddress = this.addressRepository.save(
            Address.builder()
                .user(entityManager.getReference(User.class, userId))
                .country(userAddressDetails.getCountry())
                .location(userAddressDetails.getLocation())
                .zipCode(userAddressDetails.getZipCode())
                .street(userAddressDetails.getStreet())
                .build()
        );

        return this.addressMapper.mapToDTO(createdUserAddress);
    }

    @Override
    @Transactional
    public AddressDTO updateUserAddressById(AddressDTO userAddressDetails, Long userId, Long addressId) {
        this.addressValidator.validateUserAddressIds(addressId, userAddressDetails.getId(), userId);
        final Address updatedUserAddress = this.addressRepository
            .save(Address.builder()
                .id(addressId)
                .user(this.entityManager.getReference(User.class, userId))
                .country(userAddressDetails.getCountry())
                .location(userAddressDetails.getLocation())
                .zipCode(userAddressDetails.getZipCode())
                .street(userAddressDetails.getStreet())
                .build()
            );

        return this.addressMapper.mapToDTO(updatedUserAddress);
    }

    @Override
    public Set<AddressView> findAllAddressesViewByUserId(Long userId) {
        return new HashSet<>(this.addressRepository.findAllAddressesViewByUserId(userId));
    }

    @Override
    @Transactional
    public void deleteUserAddressById(Long userId, Long addressId) {
        this.addressValidator.validateUserAddressIds(addressId, userId);
        this.entityManager.remove(this.entityManager.getReference(Address.class, addressId));
    }
}

