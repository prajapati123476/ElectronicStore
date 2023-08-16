package com.lcwd.electronics.store.services;

import com.lcwd.electronics.store.dtos.CategoryDto;
import com.lcwd.electronics.store.dtos.PageableResponse;

public interface CategoryService {

    //create
    CategoryDto create(CategoryDto categoryDto);

    //update
    CategoryDto update(CategoryDto categoryDto, String categoryId);

    //delete
    void delete(String categoryId);

    //get all
    PageableResponse<CategoryDto> getAll(int pageNumber, int pageSize, String sortBy, String sortDir);

    //get single category detail
    CategoryDto get(String categoryId);

    //search

}
