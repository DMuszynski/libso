package pl.dmuszynski.libso.mapper;

import pl.dmuszynski.libso.payload.dto.ProductDTO;
import pl.dmuszynski.libso.model.Product;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    ProductDTO mapToDto(Product mappedProduct);
    Product mapToModel(ProductDTO mappedProductDto);
}