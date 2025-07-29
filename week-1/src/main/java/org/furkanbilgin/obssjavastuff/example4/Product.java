package org.furkanbilgin.obssjavastuff.example4;

public class Product {
    private final ProductCategory category;
    private final String name;
    private final double price;

    public Product(ProductCategory category, String name, double price) {
        this.category = category;
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public ProductCategory getCategory() {
        return category;
    }
}
