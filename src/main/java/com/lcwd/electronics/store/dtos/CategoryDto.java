package com.lcwd.electronics.store.dtos;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CategoryDto {

    private String categoryId;
    @NotBlank
    @Size(min = 4, max = 50, message = "title must be of 4 char")
    private String title;
    @NotBlank(message = "description required")
    private String description;
    @NotBlank(message = "cover image required")
    private String coverImage;
    //other attribute if any
}
