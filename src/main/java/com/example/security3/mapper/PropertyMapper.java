package com.example.security3.mapper;

import com.example.security3.entity.EntProduct;
import com.example.security3.entity.EntProperty;
import com.example.security3.payload.ProductCreateDTO;
import com.example.security3.payload.PropertyCreateDTO;
import com.example.security3.payload.PropertyDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PropertyMapper {
    PropertyDTO toDto(EntProperty entProperty);

    EntProperty fromDto(PropertyCreateDTO propertyCreateDTO);

    @Mapping(target = "propertyId", source = "id")
    @Mapping(target = "id", ignore = true)
    List<PropertyDTO> toDto(List<EntProperty> properties);

    List<EntProperty> toEnt(List<PropertyDTO> propertyDTOList);

    void update(@MappingTarget EntProperty entProperty, PropertyCreateDTO propertyCreateDTO);

}
