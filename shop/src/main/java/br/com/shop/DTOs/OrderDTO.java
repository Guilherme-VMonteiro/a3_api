package br.com.shop.DTOs;

import java.util.Date;
import java.util.List;

public class OrderDTO {

    private Long id;
    private Date orderDate;
    private Float totalPrice;
    private List<ProductDTO> products;

    public OrderDTO() {
    }

    public OrderDTO(Long id, Date orderDate, Float totalPrice, List<ProductDTO> products) {
        this.id = id;
        this.orderDate = orderDate;
        this.totalPrice = totalPrice;
        this.products = products;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public Float getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Float totalPrice) {
        this.totalPrice = totalPrice;
    }

    public List<ProductDTO> getProducts() {
        return products;
    }

    public void setProducts(List<ProductDTO> products) {
        this.products = products;
    }
}
