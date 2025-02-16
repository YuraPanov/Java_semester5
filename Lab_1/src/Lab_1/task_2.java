package Lab_1;

import java.util.Scanner;

public class task_2 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        System.out.print("\nLab_1.task_2\nЧисло чисел: ");
        int n = in.nextInt();
        int sum = 0;

        for (int i = 0; i < n; i++) {
            System.out.print("Входное число " + (i + 1) + ": ");
            int number = in.nextInt();
            if (i % 2 == 0) {
                sum += number;
            } else {
                sum -= number;
            }
        }

        System.out.println("Сумма: " + sum);
        in.close();
    }
}