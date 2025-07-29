package org.furkanbilgin.obssjavastuff.example1;

public class Pen {

    public void drawShape(Shape shape) {
        shape.draw();
    }

    public void changeColor(Shape shape, String color) {
        shape.setColor(color);
        System.out.println("Shape now has the color " + color);
    }
}
