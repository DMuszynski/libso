package pl.dmuszynski.libso.mapper;

import pl.dmuszynski.libso.payload.dto.AddressDTO;
import pl.dmuszynski.libso.model.Address;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AddressMapper {
    AddressDTO mapToDTO(Address mappedAddress);
}