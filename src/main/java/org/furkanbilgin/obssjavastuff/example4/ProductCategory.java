package org.furkanbilgin.obssjavastuff.example4;

public enum ProductCategory {
    ELECTRONICS(0.10), CLOTHING(0.15), GROCERY(0.05);

    private final double discount;

    ProductCategory(double discount) {
        this.discount = discount;
    }

    public double getDiscount() {
        return discount;
    }
}
