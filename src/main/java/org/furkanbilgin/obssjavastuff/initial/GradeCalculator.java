
import java.util.Scanner;

public class GradeCalculator {

    public static void main(String[] args) {
        System.out.println("Type in the amount of classes you take:");
        Scanner scanner = new Scanner(System.in);
        int classCount = scanner.nextInt();
        int gradeSum = 0;
        for (int i = 0; i < classCount; i++) {
            System.out.println("Your grade for class " + Integer.toString(i + 1));
            int grade = scanner.nextInt();
            if (grade < 0 || grade > 100) {
                System.out.println("Grade must be greater than 0 and be less than 100.");
                i--;
                continue;
            }
            gradeSum += grade;
        }

        System.out.println("Your average grade is: " + Integer.toString((gradeSum / classCount)));
    }
}
