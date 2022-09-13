package ru.mal.model.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValueCheckStrategy;
import ru.mal.model.Product;
import ru.mal.model.dto.ProductDto;

@Mapper(componentModel = "spring", nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public interface ProductDtoMapper {

    @Mapping(target = "id", ignore = true)
    Product map(ProductDto dto);

    ProductDto map(Product product);

}