package com.lcwd.electronics.store.services.impl;

import com.lcwd.electronics.store.dtos.CategoryDto;
import com.lcwd.electronics.store.dtos.PageableResponse;
import com.lcwd.electronics.store.entities.Category;
import com.lcwd.electronics.store.exceptions.ResourceNotFoundException;
import com.lcwd.electronics.store.helper.Helper;
import com.lcwd.electronics.store.repositories.CategoryRepository;
import com.lcwd.electronics.store.services.CategoryService;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private ModelMapper mapper;

    @Value("${category.profile.image.path}")
    private String imagePath;

    private final Logger logger = LoggerFactory.getLogger(CategoryService.class);

    @Override
    public CategoryDto create(CategoryDto categoryDto) {
        //creating category id randomly
        String categoryId = UUID.randomUUID().toString();
        categoryDto.setCategoryId(categoryId);

        Category category = mapper.map(categoryDto, Category.class);
        Category savedCategory = categoryRepository.save(category);
        CategoryDto categoryDto1 = mapper.map(savedCategory, CategoryDto.class);
        return categoryDto1;
    }

    @Override
    public CategoryDto update(CategoryDto categoryDto, String categoryId) {
        //get category
        Category category = categoryRepository.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("id not found"));

        //update category details
        category.setTitle(categoryDto.getTitle());
        category.setDescription(categoryDto.getDescription());
        category.setCoverImage(categoryDto.getCoverImage());
        Category updatedCategory = categoryRepository.save(category);
        CategoryDto categoryDto1 = mapper.map(updatedCategory, CategoryDto.class);
        return categoryDto1;
    }

    @Override
    public void delete(String categoryId) {
        //get category of given id
        Category category = categoryRepository.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("not found id"));

//delete image

        String fullPath = imagePath + category.getCoverImage();
        try {
            Path path = Paths.get(fullPath);
            Files.delete(path);
        } catch (NoSuchFileException e) {
            logger.info("user image not found");
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        categoryRepository.delete(category);

    }

    @Override
    public PageableResponse<CategoryDto> getAll(int pageNumber, int pageSize, String sortBy, String sortDir) {
        Sort sort = (sortDir.equalsIgnoreCase("desc")) ? (Sort.by(sortBy).descending()): (Sort.by(sortBy).ascending());

        Pageable pageable = PageRequest.of(pageNumber, pageSize, sort);
        Page<Category> page = categoryRepository.findAll(pageable);
        PageableResponse<CategoryDto> pageableResponse = Helper.getPageableResponse(page, CategoryDto.class);
        return pageableResponse;
    }

    @Override
    public CategoryDto get(String categoryId) {
        Category category = categoryRepository.findById(categoryId).orElseThrow( () -> new ResourceNotFoundException("not found"));
        return mapper.map(category, CategoryDto.class);
    }

    @Override
    public List<CategoryDto> searchCategory(String keyword) {
        List<Category> categoriesList = categoryRepository.findByTitleContaining(keyword);
        System.out.println(keyword);

        System.out.println(categoriesList.get(0).getDescription());


        //List<CategoryDto> categoryDtoList = categoriesList.stream().map(category -> mapper.map(categoriesList, CategoryDto.class)).collect(Collectors.toList());
        List<CategoryDto> dtoList = categoriesList.stream().map(user -> entityToDto(user)).collect(Collectors.toList());


        System.out.println(dtoList.get(0).getDescription());

        return dtoList;
    }


    private CategoryDto entityToDto(Category saveUser) {

        return mapper.map(saveUser, CategoryDto.class);
    }

    private Category dtoToEntity(CategoryDto categoryDto) {

        return mapper.map(categoryDto, Category.class);
    }
}
