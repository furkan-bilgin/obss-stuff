package org.furkanbilgin.obssjavastuff.example1;

public class Circle extends Shape {

    private final int radius;

    public Circle(int radius, String color) {
        super(color);
        this.radius = radius;
    }

    public int getRadius() {
        return radius;
    }

    @Override
    public void draw() {
        System.out.printf(Double.toString(Math.PI * Math.pow(Double.valueOf(this.getRadius()), 2)) + "\n");
    }
}
