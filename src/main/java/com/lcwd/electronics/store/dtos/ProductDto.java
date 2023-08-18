package com.lcwd.electronics.store.dtos;

import com.lcwd.electronics.store.entities.Category;
import jakarta.persistence.Column;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class ProductDto {
    private String productId;
    private String title;
    private String description;
    private int price;
    private int discountedPrice;
    private int quantity;
    private Date addedDate;
    private boolean live;
    private boolean stock;
    private String productImage;
    private CategoryDto category;
}
