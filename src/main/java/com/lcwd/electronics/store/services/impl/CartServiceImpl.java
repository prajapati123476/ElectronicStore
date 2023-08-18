package com.lcwd.electronics.store.services.impl;

import com.lcwd.electronics.store.dtos.AddItemToCartRequest;
import com.lcwd.electronics.store.dtos.CartDto;
import com.lcwd.electronics.store.entities.Cart;
import com.lcwd.electronics.store.entities.CartItem;
import com.lcwd.electronics.store.entities.Product;
import com.lcwd.electronics.store.entities.User;
import com.lcwd.electronics.store.exceptions.BadApiRequest;
import com.lcwd.electronics.store.exceptions.ResourceNotFoundException;
import com.lcwd.electronics.store.repositories.CartItemRepository;
import com.lcwd.electronics.store.repositories.CartRepository;
import com.lcwd.electronics.store.repositories.ProductRepository;
import com.lcwd.electronics.store.repositories.UserRepository;
import com.lcwd.electronics.store.services.CartService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

@Service
public class CartServiceImpl implements CartService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private ModelMapper mapper;

    @Autowired
    private CartItemRepository cartItemRepository;


    @Override
    public CartDto addItemToCart(String userId, AddItemToCartRequest request) {
        int quantity = request.getQuantity();
        String productId = request.getProductId();
        if(quantity <= 0) {
            throw new BadApiRequest("requested quantity is not valid !!");
        }

        //fetch the product
        Product product = productRepository.findById(productId).orElseThrow(() -> new ResourceNotFoundException("product not found !!"));

        //fetch user
        User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("not found"));

        Cart cart = null;

        try {
            cart = cartRepository.findByUser(user).get();

        } catch (NoSuchElementException e) {
            cart = new Cart();
            cart.setCartId(UUID.randomUUID().toString());
            cart.setCreatedAt(new Date());
        }

        //perform cart operation
        //if cart item already present: then update
        AtomicReference<Boolean> updated = new AtomicReference<>(false);
        List<CartItem> items = cart.getItems();
        items.stream().map(item -> {
            if(item.getProduct().getProductId().equals(productId)) {
                //item already present in cart
                item.setQuantity(quantity);
                item.setTotalPrice(quantity*product.getPrice());
                updated.set(true);
            }
            return item;

        }).collect(Collectors.toList());

        //create items
        if(!updated.get()) {

            CartItem cartItem = CartItem.builder()
                    .quantity(quantity)
                    .totalPrice(quantity * product.getPrice())
                    .cart(cart)
                    .product(product)
                    .build();
            cart.getItems().add(cartItem);

        }
        cart.setUser(user);
        Cart updatedCart = cartRepository.save(cart);
        return mapper.map(updatedCart, CartDto.class);
    }


    @Override
    public void removeItemFromCart(String userId, int cartItem) {
        //conditions

        CartItem cartItem1 = cartItemRepository.findById(cartItem).orElseThrow(() -> new ResourceNotFoundException("not found"));
        cartItemRepository.delete(cartItem1);

    }

    @Override
    public void clearCart(String userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("not found"));
        Cart cart = cartRepository.findByUser(user).orElseThrow(() -> new ResourceNotFoundException("not found"));
        cart.getItems().clear();
        cartRepository.save(cart);

    }

    @Override
    public CartDto getCartByUser(String userId) {

        User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("not found"));
        Cart cart = cartRepository.findByUser(user).orElseThrow(() -> new ResourceNotFoundException("not found"));

        return mapper.map(cart, CartDto.class);
    }
}
