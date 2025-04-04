package com.example.dio.service.impl;

import com.example.dio.dto.response.BillResponse;
import com.example.dio.enums.OrderStatus;
import com.example.dio.enums.TableStatus;
import com.example.dio.exception.NoBillFoundException;
import com.example.dio.mapper.BillMapper;
import com.example.dio.model.Bill;
import com.example.dio.model.Order;
import com.example.dio.model.Restaurant;
import com.example.dio.model.RestaurantTable;
import com.example.dio.repository.*;
import com.example.dio.service.BillService;
import com.example.dio.utility.PDFGenerator;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

@Service
@AllArgsConstructor
public class BillServiceImpl implements BillService {

    private final BillRepository billRepository;
    private final TableRepository tableRepository;
    private final OrderRepository orderRepository;
    private final BillMapper billMapper;
    private final PDFGenerator billGenerator;
    private final RestaurantRepository restaurantRepository;
    private final CartItemRepository cartItemRepository;

    @Override
    @Transactional
    public BillResponse createBill(long tableId) {

        RestaurantTable restaurantTable = tableRepository.findById(tableId)
                .orElseThrow(() -> new NoSuchElementException("Table not found !!"));

        List<Order> orderList = orderRepository.findByRestaurantTable(restaurantTable, OrderStatus.PAID);

        double totalAmount = orderList.stream()
                .mapToDouble(Order::getTotalAmount)
                .sum();

        if (!orderList.isEmpty()) {

            Bill bill = new Bill();
            bill.setOrders(orderList);
            bill.setTotalPayableAmount(totalAmount);
            billRepository.save(bill);

            orderList.forEach(order -> order.setOrderStatus(OrderStatus.PAID));
            orderRepository.saveAll(orderList);

            restaurantTable.setStatus(TableStatus.AVAILABLE);
            tableRepository.save(restaurantTable);

            return billMapper.mapToBillResponse(bill);
        } else {
            throw new NoSuchElementException(" No CartItem Selected !! ");
        }
    }

    @Override
    public BillResponse findById(long billId) {
        Bill bill = billRepository.findById(billId)
                .orElseThrow(() -> new NoBillFoundException("No bill found with " + billId + " id"));
        return billMapper.mapToBillResponse(bill);
    }

    @Override
    public byte[] pdfGenerator(long billId) throws IOException {

        BillResponse response = this.findById(billId);

        long foodId = response.getOrders().getFirst().getCartItems().getFirst().getFoodItem().getId();

        Restaurant restaurantName = restaurantRepository.findNameByFoodItems_Id(foodId);

        long orderId = response.getOrders().getFirst().getOrderId();

        Order order = orderRepository.findRestaurantTableByOrderId(orderId);

        System.out.println(response);

        Map<String, Object> bill = Map.of("restaurantName", restaurantName.getName(),
                "tableNo",order.getRestaurantTable().getTableNumber(),
                "bill", response);

        return billGenerator.generatePdf("billUI", bill);
    }
}
