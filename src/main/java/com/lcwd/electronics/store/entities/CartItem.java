package com.lcwd.electronics.store.entities;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "cart_items")
public class CartItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int cartItemId;
    @OneToOne
    private Product product;
    private int quantity;
    private int totalPrice;

    //mapping cart
    @ManyToOne(fetch = FetchType.LAZY)
    private Cart cart;
}
