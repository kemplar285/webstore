package com.shop.webstore.data.model.product;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import io.swagger.v3.oas.annotations.media.Schema;

@JsonPropertyOrder({ "id", "name", "price", "inStock", "description"})
@JsonInclude(JsonInclude.Include.NON_DEFAULT)
@JsonIgnoreProperties(ignoreUnknown = true)
public class ProductDTO {
    @JsonProperty("id")
    @Schema(accessMode = Schema.AccessMode.READ_ONLY)
    private Long id;
    @JsonProperty("name")
    private String name;
    @JsonProperty("price")
    private double price;
    @JsonProperty("inStock")
    private int inStock;
    @JsonProperty("description")
    private String description;
    //todo: profile pic

    public ProductDTO() {
    }

    public ProductDTO(Long id, String name, double price, int inStock, String description) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.inStock = inStock;
        this.description = description;
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
        return "ProductDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", inStock=" + inStock +
                ", description='" + description + '\'' +
                '}';
    }
}
