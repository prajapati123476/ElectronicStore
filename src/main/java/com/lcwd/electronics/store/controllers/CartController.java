package com.lcwd.electronics.store.controllers;

import com.lcwd.electronics.store.dtos.AddItemToCartRequest;
import com.lcwd.electronics.store.dtos.ApiResponseMessage;
import com.lcwd.electronics.store.dtos.CartDto;
import com.lcwd.electronics.store.services.CartService;
import com.lcwd.electronics.store.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/carts")
public class CartController {

    @Autowired
    private CartService cartService;
    //add items to cart
    @PostMapping("/{userId}")
    public ResponseEntity<CartDto> addItemToCart(
            @PathVariable String userId,
            @RequestBody AddItemToCartRequest request) {

        CartDto cartDto = cartService.addItemToCart(userId, request);
        return new ResponseEntity<>(cartDto, HttpStatus.OK);
    }

    //delete item from cart
    @DeleteMapping("/{userId}/items/{itemId}")
    public ResponseEntity<ApiResponseMessage> removeItemFromCart(
            @PathVariable String userId,
            @PathVariable int itemId) {
        cartService.removeItemFromCart(userId, itemId);
        ApiResponseMessage response = ApiResponseMessage.builder()
                .message("Item is removed")
                .success(true)
                .status(HttpStatus.OK)
                .build();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    //clear item from cart
    @DeleteMapping("/{userId}")
    public ResponseEntity<ApiResponseMessage> clearItemFromCart(@PathVariable String userId) {
        cartService.clearCart(userId);
        ApiResponseMessage response = ApiResponseMessage.builder()
                .message("cart is clear")
                .success(true)
                .status(HttpStatus.OK)
                .build();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    //get cart
    @GetMapping("/{userId}")
    public ResponseEntity<CartDto> getCart(@PathVariable String userId) {
        CartDto cartDto = cartService.getCartByUser(userId);
        return new ResponseEntity<>(cartDto, HttpStatus.OK);
    }


}

