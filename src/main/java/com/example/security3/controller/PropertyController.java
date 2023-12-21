package com.example.security3.controller;

import com.example.security3.constants.ApiConstants;
import com.example.security3.payload.FilterParams;
import com.example.security3.payload.PropertyCreateDTO;
import com.example.security3.payload.PropertyDTO;
import com.example.security3.service.PropertyService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(ApiConstants.PROPERTY_API)
@Slf4j
public class PropertyController {

    private final PropertyService propertyService;


    @Operation(summary = "Get property by id")
    @GetMapping("/{id}")
    public PropertyDTO getProperty(@PathVariable Long id){
        return propertyService.getPropertyDtoById(id);
    }

    @Operation(summary = "Get property list by product id")
    @GetMapping("/{productId}")
    public List<PropertyDTO> getPorpertyList(@PathVariable Long productId){
        return propertyService.getProperties(productId);
    }

//    @GetMapping
//    public Page<PropertyDTO> getPropertyPage(FilterParams filterParams){
//        return propertyService.getPropertyPage(filterParams);
//    }

    @Operation(summary = "Create property list")
    @PostMapping
    public List<Long> createList(@RequestBody List<PropertyCreateDTO> propertyCreateDTOList){
        return propertyService.createProperties(propertyCreateDTOList);
    }

    @Operation(summary = "Delete properties")
    @DeleteMapping
    public void delete(@RequestBody List<Long> ids){
         propertyService.deleteProperties(ids);
    }






}
