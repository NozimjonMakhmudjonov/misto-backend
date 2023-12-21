package com.example.security3.service;

import com.example.security3.entity.EntProduct;
import com.example.security3.entity.EntProperty;
import com.example.security3.enums.ErrorCode;
import com.example.security3.mapper.PropertyMapper;
import com.example.security3.payload.FilterParams;
import com.example.security3.payload.PropertyCreateDTO;
import com.example.security3.payload.PropertyDTO;
import com.example.security3.repository.ProductRepository;
import com.example.security3.repository.PropertyRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PropertyService {
    private final PropertyRepository propertyRepository;
    private final PropertyMapper propertyMapper;
    private final ProductRepository productRepository;

    private EntProperty getEntPropertyById(Long id) {
        return propertyRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(String.valueOf(ErrorCode.PROPERTY_NOT_FOUND)));
    }
//    private List<Long> savePropertyList(List<PropertyCreateDTO> propertyCreateDTOS){
//        List<Long> propertyIds= new ArrayList<>();
//        for (PropertyCreateDTO propertyCreateDTO : propertyCreateDTOS) {
//            EntProperty entProperty = propertyMapper.fromDto(propertyCreateDTO);
//            EntProperty saved = propertyRepository.save(entProperty);
//            propertyIds.add(saved.getId());
//        }
//        return propertyIds;
//    }

    public PropertyDTO getPropertyDtoById(Long id) {
        return propertyMapper.toDto(getEntPropertyById(id));
    }

    public List<PropertyDTO> getProperties(Long productId) {
        Optional<EntProduct> optionalProduct = productRepository.findById(productId);
        if (optionalProduct.isPresent()){
            List<EntProperty> properties = optionalProduct.get().getProperties();
            return propertyMapper.toDto(properties);
        }
        return null;
    }

    public Page<PropertyDTO> getPropertyPage(FilterParams filterParams) {
        Page<EntProperty> propertyPage = propertyRepository.findAll(
                PageRequest.of(filterParams.getPage(), filterParams.getSize()));
        return propertyPage.map(propertyMapper::toDto);
    }


    public List<Long> createProperties(List<PropertyCreateDTO> propertyCreateDTOList) {
        List<Long> propertyIds = new ArrayList<>();
        for (PropertyCreateDTO propertyCreateDTO : propertyCreateDTOList) {
            EntProperty saved = propertyRepository.save(propertyMapper.fromDto(propertyCreateDTO));
            propertyIds.add(saved.getId());
        }
        return propertyIds;
    }

    public void deleteProperties(List<Long> ids) {
        for (Long id : ids) {
            propertyRepository.deleteById(id);
        }
    }


}
