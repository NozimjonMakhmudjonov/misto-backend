package com.example.security3.controller;
import com.example.security3.constants.ApiConstants;
import com.example.security3.payload.CategoryCreateDTO;
import com.example.security3.payload.CategoryDTO;
import com.example.security3.response.Response;
import com.example.security3.service.CategoryService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(ApiConstants.CATEGORY_API)
@Slf4j

public class CategoryController {
    private final CategoryService categoryService;

    @Operation(summary = "Get all categories")
    @GetMapping
    public Response<List<CategoryDTO>> getAll(){
        log.info("REST request to get all categories!");
        List<CategoryDTO> response=categoryService.getAll();
        return Response.ok(response);
    }

    @Operation(summary = "Get a category by id!")
    @GetMapping("/{id}")
    public Response<CategoryDTO> getById(@PathVariable long id){
        log.info("REST request to get a category by id");
        CategoryDTO response= categoryService.getById(id);
        return Response.ok(response);
    }

    @Operation(summary = "Create new category")
    @PostMapping
    public Response<CategoryDTO> create(@RequestBody CategoryCreateDTO request){
        log.info("REST request to create new category");
        CategoryDTO response= categoryService.create(request);
        return Response.ok(response);
    }

    @Operation(summary = "Update the category by id")
    @PutMapping("/{id}")
    public Response<CategoryDTO> update(@PathVariable Long id, @RequestBody CategoryCreateDTO request){
        log.info("REST request to update category by id");
        CategoryDTO response= categoryService.update(id,request);
        return Response.ok(response);
    }

    @Operation(summary = "Delete a category by id")
    @DeleteMapping("/{id}")
    public Response<Boolean> delete(@PathVariable("id") Long id){
        categoryService.delete(id);
        return Response.ok();

    }
}
