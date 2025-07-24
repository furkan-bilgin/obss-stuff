package org.furkanbilgin.obssjavastuff.example8;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class Main {
    public static void main(String[] args) {
        var books = new ArrayList<Book>();
        books.add(new Book("Orhan Pamuk", 18.99, 2002, "Kar"));
        books.add(new Book("Yaşar Kemal", 14.50, 1955, "İnce Memed"));
        books.add(new Book("Ahmet Hamdi Tanpınar", 20.00, 1949, "Huzur"));

        printBooks(filterBooksByAuthor(books, "Orhan Pamuk"));
        printBooks(filterBooksByPrice(books, 14.50));
        printBooks(filterBooksByTitle(books, "Huzur"));

    }

    private static void printBooks(List<Book> books) {
        System.out.println("Printing " + books.size() + " book(s):");
        for (Book book : books) {
            System.out.println(book.toString());
        }
    }

    private static List<Book> filterBooksByAuthor(List<Book> books, String author) {
        return filterBooks(books, (book) -> book.getAuthor().equalsIgnoreCase(author));
    }

    private static List<Book> filterBooksByPrice(List<Book> books, double price) {
        return filterBooks(books, (book) -> book.getPrice() == price);
    }

    private static List<Book> filterBooksByTitle(List<Book> books, String title) {
        return filterBooks(books, (book) -> book.getTitle().equalsIgnoreCase(title));
    }

    private static List<Book> filterBooks(List<Book> books, Function<Book, Boolean> filterLambda) {
        var filterResult = new ArrayList<Book>();
        for (var book : books) {
            if (filterLambda.apply(book)) {
                filterResult.add(book);
            }
        }
        return filterResult;
    }
}
