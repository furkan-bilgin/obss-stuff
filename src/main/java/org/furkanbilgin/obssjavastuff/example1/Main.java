package org.furkanbilgin.obssjavastuff.example1;

public class Main {

    public static void main(String[] args) {
        Circle circle = new Circle(5, "Red");
        Rectangle rectangle = new Rectangle(5, 10, "Purple");
        Pen pen = new Pen();
        pen.drawCircle(circle); // pi * 5^2 ~= 78.53
        pen.drawRectangle(rectangle); // 5*10 = 50
        pen.changeColorCircle(circle, "Blue");
        pen.changeColorRectangle(rectangle, "Pink");
    }
}
