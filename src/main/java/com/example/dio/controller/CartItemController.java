package com.example.dio.controller;

import com.example.dio.dto.response.CartItemResponse;
import com.example.dio.service.CartItemService;
import com.example.dio.utility.ResponseBuilder;
import com.example.dio.utility.ResponseStructure;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("${app.base-url}")
@AllArgsConstructor
public class CartItemController {

    private final CartItemService cartItemService;

    @PostMapping("/tables/{tableid}/cart-items/food-items/{foodid}")
    public ResponseEntity<ResponseStructure<CartItemResponse>> createCartItem(@PathVariable long tableid,
                                                                              @PathVariable long foodid ,
                                                                              @RequestParam int quantity){
        return ResponseBuilder.created("Cart item created",cartItemService.createCartItem(tableid,foodid,quantity));
    }

    @PatchMapping("/cart-items/{cartId}")
    public ResponseEntity<ResponseStructure<CartItemResponse>> updateQuantity(@PathVariable long cartId,@RequestParam int quantity){
        return ResponseBuilder.ok("cart quantity updated",cartItemService.updateQuantity(cartId,quantity));
    }
}
