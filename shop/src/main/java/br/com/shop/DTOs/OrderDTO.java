package br.com.shop.DTOs;

import java.util.List;

public class OrderDTO {

    private Long user;
    private List<Long> products;

    public OrderDTO() {
    }

    public OrderDTO(Long user, List<Long> products) {
        this.user = user;
        this.products = products;
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
}
