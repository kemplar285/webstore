package com.shop.webstore.data.model.product;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Product{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private double price;
    private int inStock;
    private String description;
    //todo: profile pic

    public Product(Long id, String name, double price, int inStock, String description) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.inStock = inStock;
        this.description = description;
    }

    public Product(String name, double price, int inStock, String description) {
        this.name = name;
        this.price = price;
        this.inStock = inStock;
        this.description = description;
    }

    public Product() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getInStock() {
        return inStock;
    }

    public void setInStock(int inStock) {
        this.inStock = inStock;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", inStock=" + inStock +
                ", description='" + description + '\'' +
                '}';
    }
}
