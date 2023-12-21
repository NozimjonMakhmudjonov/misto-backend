package com.example.security3.controller;


import com.example.security3.constants.ApiConstants;
import com.example.security3.payload.ProductCreateDTO;
import com.example.security3.payload.ProductDTO;
import com.example.security3.response.Response;
import com.example.security3.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequiredArgsConstructor
@RequestMapping(ApiConstants.PRODUCT_API)
@Slf4j
public class ProductController {
    private final ProductService productService;

    @Operation(summary = "Get all products")
    @GetMapping
    public Response<List<ProductDTO>> getAll(){
        log.info("REST request to get all products");
        List<ProductDTO> response= productService.getAll();
        return Response.ok(response);
    }

    @Operation(summary = "Get product by id")
    @GetMapping("/{id}")
    public Response<ProductDTO> getProductById(@PathVariable Long id){
        log.info("REST request to get a product by id");
        ProductDTO response= productService.getById(id);
        return Response.ok(response);
    }

    @Operation(summary = "Create new product")
    @PostMapping
    public Response<ProductDTO> create(@RequestBody ProductCreateDTO request){
        log.info("REST request to create new product");
        ProductDTO response=productService.create(request);
        return Response.ok(response);
    }

    @Operation(summary = "Update product by id")
    @PutMapping("/{id}")
    public Response<ProductDTO> update(@PathVariable("id") Long id,
                                       @RequestBody ProductCreateDTO request) {
        log.info("REST request to update product");
        ProductDTO response=productService.update(id,request);
        return Response.ok(response);
    }

    @Operation(summary = "Delete product by id")
    @DeleteMapping("/{id}")
    public Response<Boolean> delete(@PathVariable("id") Long id){
        productService.delete(id);
        return Response.ok();
    }
}
