package com.lcwd.electronics.store.services.impl;

import com.lcwd.electronics.store.dtos.UserDto;
import com.lcwd.electronics.store.entities.User;
import com.lcwd.electronics.store.repositories.UserRepository;
import com.lcwd.electronics.store.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;



    @Override
    public UserDto createUser(UserDto userDto) {
        //generate unique id in string format
        String userId = UUID.randomUUID().toString();
        userDto.setUserId(userId);

        //dto -> entity
        User user = dtoToEntity(userDto);
        User saveUser = userRepository.save(user);
        UserDto newDto = entityToDto(saveUser);
        return newDto;
    }

    private UserDto entityToDto(User saveUser) {
       return UserDto.builder()
               .userId(saveUser.getUserId())
               .name(saveUser.getName())
               .email(saveUser.getEmail())
               .about(saveUser.getAbout())
               .gender(saveUser.getGender())
               .imageName(saveUser.getImageName())
               .build();

    }

    private User dtoToEntity(UserDto userDto) {
       return User.builder()
               .userId(userDto.getUserId())
               .name(userDto.getName())
               .email(userDto.getEmail())
               .password(userDto.getPassword())
               .about(userDto.getAbout())
               .gender(userDto.getGender())
               .imageName(userDto.getImageName())
               .build();

    }
}
