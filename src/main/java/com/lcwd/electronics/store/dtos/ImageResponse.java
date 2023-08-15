package com.lcwd.electronics.store.dtos;

import lombok.*;
import org.springframework.http.HttpStatus;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ImageResponse {
        private String imageName;
        private String message;
        private Boolean success;
        private HttpStatus status;

}
