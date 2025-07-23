package org.furkanbilgin.obssjavastuff.example4;

import java.util.ArrayList;

public class ShoppingCart {
    private final ArrayList<Product> products = new ArrayList<>();

    public void addProduct(Product product) {
        products.add(product);
    }

    public double calculateTotalPrice() {
        double totalDiscount = 0;
        for (Product product : products) {
            double discount = product.getPrice() * (1 - product.getCategory().getDiscount());
            totalDiscount += discount;
        }
        return totalDiscount;
    }
}
