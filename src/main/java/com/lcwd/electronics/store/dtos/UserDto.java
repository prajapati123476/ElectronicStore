package com.lcwd.electronics.store.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
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
    @NotBlank
    private String email;
    @NotBlank(message = "password is required")
    private String password;
    @Size(min = 4, max = 6, message = "invalid gender")
    private String gender;
    @NotBlank(message = "write something about yourself")
    private String about;

    private String imageName;
}
