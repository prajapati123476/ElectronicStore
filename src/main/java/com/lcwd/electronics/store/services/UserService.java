package com.lcwd.electronics.store.services;

import com.lcwd.electronics.store.dtos.PageableResponse;
import com.lcwd.electronics.store.dtos.UserDto;
import com.lcwd.electronics.store.entities.User;

import java.io.IOException;
import java.util.List;

public interface UserService {

    UserDto createUser(UserDto userDto);

    UserDto updateUser(UserDto userDto, String userId);

    void deleteUser(String userId) throws IOException;

    PageableResponse<UserDto> getAllUser(int pageNumber, int pageSize, String sortBy, String sortDir);

    UserDto getUserById(String userId);

    UserDto getUserByEmail(String email);

    List<UserDto> searchUser(String keyword);
}
