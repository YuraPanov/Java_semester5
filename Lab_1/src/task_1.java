import java.util.Scanner;

public class task_1 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.print("\ntask_1\nInput number: ");
        int inputNumber = in.nextInt();
        int i = 0;

        while (inputNumber != 1) {
            ++i;
            if (inputNumber % 2 == 0) {
                inputNumber /= 2;
            } else {
                inputNumber = inputNumber * 3 + 1;
            }
        }

        System.out.println("Число шагов: " + i);
        in.close();
    }
}