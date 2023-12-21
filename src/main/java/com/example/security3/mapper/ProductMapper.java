package com.example.security3.mapper;

import com.example.security3.entity.EntProduct;
import com.example.security3.payload.ProductCreateDTO;
import com.example.security3.payload.ProductDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProductMapper {



    ProductDTO toDto(EntProduct entProduct);
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "attachments", ignore = true)
    @Mapping(target = "properties", ignore = true)
    EntProduct fromDto(ProductCreateDTO productCreateDTO);

    @Mapping(target = "attachments", ignore = true)
    @Mapping(target = "properties", ignore = true)
    List<ProductDTO> toDtos(List<EntProduct> entProducts);

    @Mapping(target = "attachments", ignore = true)
    @Mapping(target = "properties", ignore = true)
    void update(@MappingTarget EntProduct entProduct, ProductCreateDTO productCreateDTO);
}
