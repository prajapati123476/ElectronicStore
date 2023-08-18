package com.lcwd.electronics.store.dtos;

import com.lcwd.electronics.store.entities.OrderItem;
import lombok.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class CreateOrderRequest {

    private String cartId;
    private String userId;
    private String orderStatus="PENDING";
    private String paymentStatus="NOT_PAID";
    private String billingAddress;
    private String billingPhone;
    private String billingName;
}
