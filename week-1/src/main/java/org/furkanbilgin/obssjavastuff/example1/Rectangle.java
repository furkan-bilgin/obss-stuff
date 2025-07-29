package org.furkanbilgin.obssjavastuff.example1;

public class Rectangle extends Shape {

    private final int width;
    private final int height;

    public Rectangle(int width, int height, String color) {
        super(color);
        this.width = width;
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    @Override
    public void draw() {
        System.out.println(Integer.toString(width * height));
    }
}
