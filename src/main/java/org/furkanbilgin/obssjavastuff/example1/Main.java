package org.furkanbilgin.obssjavastuff.example1;

public class Main {

    public static void main(String[] args) {
        var circle = new Circle(5, "Red");
        var rectangle = new Rectangle(5, 10, "Purple");
        var pen = new Pen();
        pen.drawShape(circle); // pi * 5^2 ~= 78.53
        pen.drawShape(rectangle); // 5*10 = 50
        pen.changeColor(circle, "Blue");
        pen.changeColor(rectangle, "Pink");
    }
}
