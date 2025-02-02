import java.util.Objects;
import java.util.Scanner;

public class task_3 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.print("\ntask_3\nInput coordinates:\n");
        int x = in.nextInt();
        int y = in.nextInt();
        int currentX = 0;
        int currentY = 0;
        String direction = "";
        int count = 0;

        while (true) {
            if (in.hasNextInt()) {
                int steps = in.nextInt();
                if (currentX == x & currentY == y) {
                    continue;
                }
                else {
                    switch (direction) {
                        case "север":
                            currentY += steps;
                            break;
                        case "юг":
                            currentY -= steps;
                            break;
                        case "запад":
                            currentX += steps;
                            break;
                        case "восток":
                            currentX -= steps;
                            break;
                        default:
                            throw new RuntimeException("incorrect value");
                    }
                    count++;
                }
            }
            else {
                direction = in.nextLine();
                if (Objects.equals(direction, "стоп")) {
                    break;
                }
            }
        }
        System.out.println("Число шагов: " + count);
        in.close();
    }
}