package pl.dmuszynski.libso.mapper;

import pl.dmuszynski.libso.payload.dto.AuthorityDTO;
import pl.dmuszynski.libso.model.Authority;
import org.mapstruct.Mapping;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AuthorityMapper {
    @Mapping(source = "authority", target = "authorityType")
    Authority mapToModel(AuthorityDTO mappedAuthorityDTO);
}
