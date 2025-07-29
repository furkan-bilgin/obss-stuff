package org.furkanbilgin.obssjavastuff.example4;

public class Main {
    public static void main(String[] args) {
        ShoppingCart cart = new ShoppingCart();
        cart.addProduct(new Product(ProductCategory.ELECTRONICS, "Laptop", 10000));
        cart.addProduct(new Product(ProductCategory.CLOTHING, "T-Shirt", 500));
        cart.addProduct(new Product(ProductCategory.GROCERY, "Çikolata", 20));

        double total = cart.calculateTotalPrice();
        System.out.println("Sepetin indirimli toplam fiyatı: " + total + " TL");
    }
}
