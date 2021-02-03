package pl.dmuszynski.libso.api;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import pl.dmuszynski.libso.service.AddressService;
import pl.dmuszynski.libso.payload.dto.AddressDTO;
import pl.dmuszynski.libso.payload.AddressView;
import lombok.RequiredArgsConstructor;

import java.util.Set;

@RestController
@RequiredArgsConstructor
@PreAuthorize(value = "hasRole('USER')")
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(value = "libso/users/{userId}/addresses")
public class AddressController {

    private final AddressService addressService;

    @PostMapping
    public ResponseEntity<AddressView> createUserAddress(@RequestBody AddressDTO userAddressDetails, @PathVariable Long userId) {
        final AddressDTO createdAddressDto = this.addressService.createUserAddress(userAddressDetails, userId);
        return new ResponseEntity<>(createdAddressDto, HttpStatus.CREATED);
    }

    @PutMapping(value = "/{addressId}")
    public ResponseEntity<AddressView> updateUserAddressById(@RequestBody AddressDTO userAddressDetails, @PathVariable Long userId, @PathVariable Long addressId) {
        final AddressDTO updatedAddressDto = this.addressService.updateUserAddressById(userAddressDetails, userId,  addressId);
        return new ResponseEntity<>(updatedAddressDto, HttpStatus.OK);
    }

    @PatchMapping(value = "/{addressId}/delivery")
    public ResponseEntity<AddressView> updateUserAddressDeliveryById(@RequestBody AddressDTO userAddressDetails, @PathVariable Long userId, @PathVariable Long addressId) {
        final AddressDTO updatedAddressDto = this.addressService.updateUserAddressDeliveryById(userAddressDetails, userId,  addressId);
        return new ResponseEntity<>(updatedAddressDto, HttpStatus.OK);
    }
    @GetMapping
    public ResponseEntity<Set<AddressView>> findAllAddressesViewByUserId(@PathVariable Long userId) {
        final Set<AddressView> foundUserAddressesViewSet = this.addressService.findAllAddressesViewByUserId(userId);
        if (!foundUserAddressesViewSet.isEmpty())
            return new ResponseEntity<>(foundUserAddressesViewSet, HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping(value = "/{addressId}")
    public ResponseEntity<HttpStatus> deleteUserAddressById(@PathVariable Long userId, @PathVariable Long addressId) {
        this.addressService.deleteUserAddressById(userId, addressId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}