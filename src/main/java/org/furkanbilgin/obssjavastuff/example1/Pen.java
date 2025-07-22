package org.furkanbilgin.obssjavastuff.example1;

public class Pen {

    public void drawRectangle(Rectangle r) {
        System.out.println(Integer.toString(r.getWidth() * r.getHeight()));
    }

    public void drawCircle(Circle c) {
        System.out.printf(Double.toString(Math.PI * Math.pow(Double.valueOf(c.getRadius()), 2)) + "\n");
    }

    public void changeColorRectangle(Rectangle r, String color) {
        r.setColor(color);
        System.out.println("Rectangle now has the color " + color);
    }

    public void changeColorCircle(Circle c, String color) {
        c.setColor(color);
        System.out.println("Circle now has the color " + color);
    }
}
