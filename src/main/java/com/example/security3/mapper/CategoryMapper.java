package com.example.security3.mapper;

import com.example.security3.entity.EntCategory;
import com.example.security3.payload.CategoryCreateDTO;
import com.example.security3.payload.CategoryDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CategoryMapper {

    CategoryDTO toDto(EntCategory entCategory);

    @Mapping(target = "id", ignore = true)
    EntCategory fromDto(CategoryCreateDTO categoryCreateDTO);

    List<CategoryDTO> toDto(List<EntCategory> entCategories);

    @Mapping(target = "id", ignore = true)
    void update(@MappingTarget EntCategory entCategory, CategoryCreateDTO categoryCreateDTO);

}
