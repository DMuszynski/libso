package pl.dmuszynski.libso.validator;

import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.data.repository.CrudRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.dmuszynski.libso.exception.IncorrectIdException;
import pl.dmuszynski.libso.model.Address;

@Component
public class AddressValidator extends AbstractModelWithOwnerValidator<Address> {

    @Autowired
    public AddressValidator(CrudRepository<Address, Long> repository) {
        super(repository);
    }

    public void validateUserAddressIds(Long addressId, Long addressDTOId, Long userId) throws ResourceNotFoundException, IncorrectIdException {
        final Address validateAddress = this.validateWithReturnModelAndModelDtoIds(addressId, addressDTOId);
        this.equalsModelOwnerAndRequestModelOwnerIds(validateAddress.getUser(), userId);
    }

    public void validateUserAddressIds(Long addressId, Long userId) throws ResourceNotFoundException, IncorrectIdException {
        final Address validateAddress = this.validateFindModelById(addressId);
        this.equalsModelOwnerAndRequestModelOwnerIds(validateAddress.getUser(), userId);
    }
}
