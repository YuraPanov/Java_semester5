package Lab_1;

import java.util.Scanner;

public class task_5 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.print("\nLab_1.task_5\nInput number: ");
        int number = in.nextInt();

        if (number >= 100 && number < 1000) {
            int a = number / 100;
            int b = (number / 10) % 10;
            int c = number % 10;

            boolean isTwiceEven = (a * b * c) % 2 == 0 && (a + b + c) % 2 == 0;

            if (isTwiceEven) {
                System.out.println("Число " + number + " является 'дважды четным'.");
            } else {
                System.out.println("Число " + number + " не является 'дважды четным'.");
            }
        } else {
            System.out.println("Пожалуйста, введите трехзначное число.");
        }

        in.close();
    }
}
