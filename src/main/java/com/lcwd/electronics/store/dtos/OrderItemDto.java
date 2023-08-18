package com.lcwd.electronics.store.dtos;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderItemDto {

    public int orderItemId;
    private int quantity;
    private int totalPrice;
    private ProductDto product;
//    private OrderDto order;

}
