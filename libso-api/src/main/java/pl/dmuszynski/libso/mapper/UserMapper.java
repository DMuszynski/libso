package pl.dmuszynski.libso.mapper;

import pl.dmuszynski.libso.payload.dto.AuthorizedUserDTO;
import pl.dmuszynski.libso.model.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    AuthorizedUserDTO mapToDTO(User mappedUser);
}
