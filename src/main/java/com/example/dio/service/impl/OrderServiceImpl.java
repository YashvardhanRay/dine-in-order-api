package com.example.dio.service.impl;


import com.example.dio.dto.response.OrderResponse;
import com.example.dio.enums.OrderStatus;
import com.example.dio.enums.StockStatus;
import com.example.dio.enums.TableStatus;
import com.example.dio.mapper.OrderMapper;
import com.example.dio.model.CartItem;
import com.example.dio.model.FoodItem;
import com.example.dio.model.Order;
import com.example.dio.model.RestaurantTable;
import com.example.dio.repository.CartItemRepository;
import com.example.dio.repository.FoodItemRepository;
import com.example.dio.repository.OrderRepository;
import com.example.dio.repository.TableRepository;
import com.example.dio.service.OrderService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@AllArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final TableRepository tableRepository;
    private final CartItemRepository cartItemRepository;
    private final OrderMapper orderMapper;
    private final FoodItemRepository foodItemRepository;


    @Override
    @Transactional
    public OrderResponse createOrder(long tableId) {
        RestaurantTable restaurantTable = tableRepository.findById(tableId)
                .orElseThrow(() -> new NoSuchElementException("Table not found !!"));

        List<CartItem> cartItemList = cartItemRepository.findByRestaurantTable(restaurantTable);

        Order order = null ;

        if(!cartItemList.isEmpty()){
            order = new Order();
            order.setOrderStatus(OrderStatus.ORDERED);
            order.setCartItems(cartItemList);
            order.setRestaurantTable(restaurantTable);
            order.setTotalAmount(
                    cartItemList.stream()
                            .mapToDouble(CartItem::getTotalPrice)
                            .sum());
            orderRepository.save(order);
        }
        else{
            throw new NoSuchElementException(" No CartItem Selected !! ");
        }

        restaurantTable.setStatus(TableStatus.OCCUPIED);
        tableRepository.save(restaurantTable);

        cartItemList.forEach(item -> item.setOrdered(true));
        cartItemList.forEach(cartItem -> {

            FoodItem foodItem = cartItem.getFoodItem();
            if ((foodItem.getStock() - cartItem.getQuantity() > 0)) {
                foodItem.setAvailability(StockStatus.STOCK_IN);
            } else foodItem.setAvailability(StockStatus.STOCK_OUT);

            foodItem.setStock(foodItem.getStock()-cartItem.getQuantity());
            foodItemRepository.save(foodItem);
        });
        cartItemRepository.saveAll(cartItemList);

        return orderMapper.mapToOrderResponse(order);
    }
}
