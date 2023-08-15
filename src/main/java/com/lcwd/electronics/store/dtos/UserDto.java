package com.lcwd.electronics.store.dtos;

import com.lcwd.electronics.store.validate.ImageNameValid;
import jakarta.validation.constraints.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDto {

    private String userId;

    @Size(min = 3, max = 40, message = "invalid name")
    private String name;
    @Email(message = "invalid user email!!")
    @Pattern(regexp = "^[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+$", message = "invalid email ")
    @NotBlank
    private String email;
    @NotBlank(message = "password is required")
    private String password;
    @Size(min = 4, max = 6, message = "invalid gender")
    private String gender;
    @NotBlank(message = "write something about yourself")
    private String about;
    //cutom validator annotation
    @ImageNameValid
    private String imageName;
}
