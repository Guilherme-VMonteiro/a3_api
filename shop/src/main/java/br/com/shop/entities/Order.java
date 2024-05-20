package br.com.shop.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.Date;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne()
    @JoinColumn(name = "user_id")
    private User user;

    @Column(nullable = false)
    @Temporal(TemporalType.DATE)
    private Date orderDate;

    @Column(nullable = false)
    private Float totalPrice;

    @ManyToMany()
    @JoinTable(
            name = "order_products",
            joinColumns = @JoinColumn(name = "order_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id"))
    private List<Product> products;

    public Order() {
    }

    public Order(User user, Date orderDate, Float totalPrice, List<Product> products) {
        this.user = user;
        this.orderDate = orderDate;
        this.totalPrice = totalPrice;
        this.products = products;
    }

    public Order(Long id, User user, Date orderDate, Float totalPrice, List<Product> products) {
        this.id = id;
        this.user = user;
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
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

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return Objects.equals(id, order.id) && Objects.equals(user, order.user) && Objects.equals(orderDate, order.orderDate) && Objects.equals(totalPrice, order.totalPrice) && Objects.equals(products, order.products);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, user, orderDate, totalPrice, products);
    }
}
