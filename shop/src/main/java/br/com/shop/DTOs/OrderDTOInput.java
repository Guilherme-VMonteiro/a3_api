package br.com.shop.DTOs;

import br.com.shop.entities.OrderStatus;

import java.util.List;

public class OrderDTOInput {

    private Long user;
    private List<Long> products;
    private OrderStatus orderStatus;

    public OrderDTOInput() {
    }

    public OrderDTOInput(Long user, List<Long> products) {
        this.user = user;
        this.products = products;
        this.orderStatus = OrderStatus.AWAITING_PAYMENT;
    }

    public OrderDTOInput(Long user, List<Long> products, OrderStatus orderStatus) {
        this.user = user;
        this.products = products;
        this.orderStatus = orderStatus;
    }

    public Long getUser() {
        return user;
    }

    public void setUser(Long user) {
        this.user = user;
    }

    public List<Long> getProducts() {
        return products;
    }

    public void setProducts(List<Long> products) {
        this.products = products;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }
}
