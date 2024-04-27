package com.webstore.web;

import com.webstore.service.OrderService;
import com.webstore.service.dto.OrderDTO;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
public class OrderController {
    @Autowired
    OrderService service;

    @GetMapping("/api/orders")
    public ResponseEntity<?> getAllOrders() {
        List<OrderDTO> data = service.getAllOrders();
        OrdersInfo info = new OrdersInfo(data);
        return new ResponseEntity<>(info, HttpStatus.OK);
    }
    @GetMapping("/api/orders/{orderId}")
    public ResponseEntity<?> getOrderDetail(@PathVariable("orderId") String orderId) {
        OrderDTO data = service.getOrderDetail(orderId);
        return new ResponseEntity<>(data, HttpStatus.OK);
    }

    @PostMapping("/api/orders")
    public ResponseEntity<?> createOrder(@RequestBody @Valid OrderDTO orderDTO) {
        OrderDTO data = service.createOrder(orderDTO);
        return new ResponseEntity<>(data, HttpStatus.OK);
    }

    @PostMapping("/api/orders/{orderId}")
    public ResponseEntity<?> updateStatus(@PathVariable("orderId") String orderId,@RequestBody OrderStatus status) {
        service.updateOrderStatus(orderId, status.getStatus());
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/api/orders/search")
    public ResponseEntity<?> searchOrdersByStatus(@RequestParam("status") String status) {
        List<OrderDTO> data = service.findOrdersByStatus(status);
        OrdersInfo info = new OrdersInfo(data);
        return new ResponseEntity<>(info, HttpStatus.OK);
    }
    @DeleteMapping("/api/orders/{orderId}")
    public ResponseEntity<?> deleteOrder(@PathVariable("orderId") String orderId) {
        service.removeOrder(orderId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);//204
    }
}
