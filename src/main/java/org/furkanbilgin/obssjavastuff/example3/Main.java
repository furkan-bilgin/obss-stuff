package org.furkanbilgin.obssjavastuff.example3;

public class Main {
    static final int TRIANGLE = 1;
    static final int SQUARE = 2;
    static final int RECTANGLE = 3;
    static final int CIRCLE = 4;
    static final int QUIT = 5;

    public static void main(String[] args) {
        try (java.util.Scanner scanner = new java.util.Scanner(System.in)) {
            while (true) {
                System.out.println("Alan hesaplamak istediğiniz çokgeni seçin");
                System.out.println(TRIANGLE + " - Üçgen");
                System.out.println(SQUARE + " - Kare");
                System.out.println(RECTANGLE + " - Dikdörtgen");
                System.out.println(CIRCLE + " - Daire");
                System.out.println(QUIT + " - Çıkış");
                System.out.print("> ");
                int choice = scanner.nextInt();
                double result;
                /*
                 * # Nasıl geliştirilebilir?
                 * TRIANGLE, SQUARE gibi komutları ayrı bir sınıfa koyup, bütün
                 * komutlar için aynı 'Command' sınıfını inherit ederek kullanabiliriz.
                 * Örneğin: TriangleCommand, Command sınıfını extend eder,
                 * choice değerine göre commandId->Command pair'lerinin bulunduğu
                 * dictionary'den Command bulunur ve Command.execute() çağırılır.
                 * Hatta daha iyisi, dictionary yerine reflection ile sınıflardaki
                 * Command.commandId bulup buna göre Command.execute() çağırılır.
                 */
                switch (choice) {
                    case TRIANGLE -> {
                        System.out.print("Taban alanı:\n> ");
                        double base = scanner.nextDouble();
                        System.out.print("Yükseklik:\n> ");
                        double height = scanner.nextDouble();
                        result = base * height / 2;
                        System.out.println("Sonuç: " + base + "*" + height + "/2 = " + result);
                    }
                    case SQUARE -> {
                        System.out.print("Kenar uzunluğu:\n> ");
                        double side = scanner.nextDouble();
                        result = side * side;
                        System.out.println("Sonuç: " + side + "*" + side + " = " + result);
                    }
                    case RECTANGLE -> {
                        System.out.print("Kısa kenar uzunluğu:\n> ");
                        double shortSide = scanner.nextDouble();
                        System.out.print("Uzun kenar uzunluğu:\n> ");
                        double longSide = scanner.nextDouble();
                        result = shortSide * longSide;
                        System.out.println("Sonuç: " + shortSide + "*" + longSide + " = " + result);
                    }
                    case CIRCLE -> {
                        System.out.print("Yarıçap:\n> ");
                        double radius = scanner.nextDouble();
                        result = Math.PI * radius * radius;
                        System.out.println("Sonuç: " + Math.PI + "*" + radius + "*" + radius + " = " + result);
                    }
                    case QUIT -> System.exit(0);
                    default -> System.out.println("Geçersiz seçim.");
                }
                System.out.println("---------");
            }
        }
    }
}
