package com.example.security3.service;

import com.example.security3.entity.EntAttachment;
import com.example.security3.entity.EntProduct;
import com.example.security3.entity.EntProperty;
import com.example.security3.enums.ErrorCode;
import com.example.security3.mapper.ProductMapper;
import com.example.security3.mapper.PropertyMapper;
import com.example.security3.payload.ProductCreateDTO;
import com.example.security3.payload.ProductDTO;
import com.example.security3.payload.PropertyDTO;
import com.example.security3.repository.AttachmentRepository;
import com.example.security3.repository.ProductRepository;
import com.example.security3.repository.PropertyRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;
    private final ProductMapper productMapper;
    private final PropertyRepository propertyRepository;
    private final PropertyMapper propertyMapper;
    private final AttachmentRepository attachmentRepository;


    private EntProduct getEntProductById(Long id) {
        return productRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException(String.valueOf(ErrorCode.PRODUCT_NOT_FOUND))
        );
    }

    private List<String> getAttachmentNames(Long productId) {
        EntProduct entProduct = getEntProductById(productId);
        List<EntAttachment> attachments = entProduct.getAttachments();
        List<String> attachmentNames = new ArrayList<>();
        for (EntAttachment attachment : attachments) {
            attachmentNames.add(attachment.getName());
        }
        return attachmentNames;
    }

//    private List<Long> getPropertyIds(Long productId) {
//        EntProduct entProduct = getEntProductById(productId);
//        List<Long> propertyIds = new ArrayList<>();
//        List<EntProperty> properties = entProduct.getProperties();
//        for (EntProperty property : properties) {
//            propertyIds.add(property.getId());
//        }
//        return propertyIds;
//    }

    private List<PropertyDTO> getProperties(List<Long> propertyIds) {
        List<PropertyDTO> propertyDTOList = new ArrayList<>();
        for (Long propertyId : propertyIds) {
            Optional<EntProperty> entProperty = propertyRepository.findById(propertyId);
            entProperty.ifPresent(property -> propertyDTOList.add(propertyMapper.toDto(property)));
        }
        return propertyDTOList;
    }

    private List<EntAttachment> getAttachments(List<String> attachmentNames) {
        List<EntAttachment> attachmentDTOList = new ArrayList<>();
        for (String attachmentName : attachmentNames) {
            Optional<EntAttachment> byName = attachmentRepository.findByName(attachmentName);
            byName.ifPresent(attachmentDTOList::add);
        }
        return attachmentDTOList;
    }

    public List<ProductDTO> getAll() {
        List<EntProduct> products = productRepository.findAll();
        return productMapper.toDtos(products);
    }

    @Transactional
    public ProductDTO getById(Long id) {
        EntProduct product = getEntProductById(id);
        return productMapper.toDto(product);
    }

    @Transactional
    public ProductDTO create(ProductCreateDTO request) {
        EntProduct entProduct = productMapper.fromDto(request);
        entProduct.setProperties(propertyMapper.toEnt(getProperties(request.getProperties())));
        entProduct.setAttachments(getAttachments(request.getAttachments()));
        entProduct = productRepository.save(entProduct);
        return productMapper.toDto(entProduct);
    }

    @Transactional
    public ProductDTO update(Long id, ProductCreateDTO request) {
        EntProduct entProduct = getEntProductById(id);
        productMapper.update(entProduct, request);
        entProduct.setProperties(propertyMapper.toEnt(getProperties(request.getProperties())));
        entProduct.setAttachments(getAttachments(request.getAttachments()));
        entProduct = productRepository.save(entProduct);
        return productMapper.toDto(entProduct);
    }

    public void delete(Long id) {
        EntProduct entProduct = getEntProductById(id);
        productRepository.delete(entProduct);
    }

}
