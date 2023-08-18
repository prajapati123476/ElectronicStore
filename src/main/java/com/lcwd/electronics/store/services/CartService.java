package com.lcwd.electronics.store.services;

import com.lcwd.electronics.store.dtos.AddItemToCartRequest;
import com.lcwd.electronics.store.dtos.CartDto;

public interface CartService {

    //add items to cart
    //case1: cart for user is not available: we will create the cart and then add
    //case2: cart available add the item to cart
    CartDto addItemToCart(String userId, AddItemToCartRequest request);

    //remove item from cart
    void  removeItemFromCart(String userId, int cartItem);

    void clearCart(String userId);

    CartDto getCartByUser(String userId);


}
