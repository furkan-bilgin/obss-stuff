package furkanbilgin.obssstuff;

import java.io.*;
import java.util.ArrayList;
import java.util.Random;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

@WebServlet("/random")
public class RandomGeneratorServlet extends HttpServlet {
    private final ArrayList<Integer> generatedNumbers = new ArrayList<>();
    private final Random random = new Random();

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        System.out.println("Running GET");

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        for (int number : generatedNumbers) {
            out.println(number);
        }
    }


    @Override
    public void doPut(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        System.out.println("Running PUT");

        int generatedNumber = generateRandomNumber();
        generatedNumbers.add(generatedNumber);

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println(generatedNumber);
    }

    @Override
    public void doPatch(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        System.out.println("Running PATCH");
        if (generatedNumbers.isEmpty()) {
            throw new RuntimeException("No number to replace");
        }

        int lastNumber = generatedNumbers.get(generatedNumbers.size() - 1);
        generatedNumbers.remove(generatedNumbers.size() - 1);
        int newNumber = generateRandomNumber();
        generatedNumbers.add(newNumber);
        PrintWriter out = response.getWriter();

        out.println("Old number:" + Integer.toString(lastNumber));
        out.println("New number:" + Integer.toString(newNumber));
    }

    @Override
    public void doDelete(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        if (generatedNumbers.isEmpty()) {
            throw new RuntimeException("No number to replace");
        }
        System.out.println("Running DELETE");

        int deletedNumber = generatedNumbers.get(generatedNumbers.size() - 1);
        generatedNumbers.remove(generatedNumbers.size() - 1);

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("Deleted number:" + Integer.toString(deletedNumber));
    }

    private int generateRandomNumber() {
        return random.nextInt(100);
    }
}