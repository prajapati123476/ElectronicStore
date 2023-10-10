package com.lcwd.electronics.store.dtos;

import jakarta.validation.constraints.NotBlank;
import lombok.*;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class CreateOrderRequest {

    @NotBlank(message = "cart id is required!!")
    private String cartId;
    @NotBlank(message = "user id is required!!")
    private String userId;
    private String orderStatus="PENDING";
    private String paymentStatus="NOT_PAID";
    @NotBlank(message = "address is required!!")
    private String billingAddress;
    @NotBlank(message = "phone is required!!")
    private String billingPhone;
    @NotBlank(message = "billing name is required!!")
    private String billingName;
}
