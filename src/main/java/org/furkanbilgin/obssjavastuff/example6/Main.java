package org.furkanbilgin.obssjavastuff.example6;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class Main {
    public static void main(String[] args) throws FileNotFoundException, IOException, ClassNotFoundException {
        // Serialize
        try (java.io.FileOutputStream fileOutput = new FileOutputStream("student.txt");
                java.io.ObjectOutputStream objectOutput = new ObjectOutputStream(fileOutput)) {
            objectOutput.writeObject(
                    new Student("12345", "Mahmut", "Veli",
                            new Course("CS101", "Introduction to Computer Science")));
        }
        // Deserialize
        try (java.io.FileInputStream fileInput = new java.io.FileInputStream("student.txt");
                java.io.ObjectInputStream objectInput = new java.io.ObjectInputStream(fileInput)) {
            Student student = (Student) objectInput.readObject();
            System.out.println(student.getName() + ", " + student.getSurname());
            System.out.println(student.getCourse().getCourseName());
        }
    }
}
