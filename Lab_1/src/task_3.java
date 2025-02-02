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
        String 4 = "";
        int count = 0;

        while (true) {
            String direction = in.next();
            if (Objects.equals(direction, "stop")) {
                break;
            }
            int steps = in.nextInt();
            switch (direction) {
                case "north":
                    currentY += steps;
                    break;
                case "south":
                    currentY -= steps;
                    break;
                case "east":
                    currentX += steps;
                    break;
                case "west":
                    currentX -= steps;
                    break;
                default:
                    System.out.println("Incorrect direction input: " + direction);
                    continue;
            }

            count++;


            if (currentX == x && currentY == y) {
                break;
            }
        }
        System.out.println("Число шагов: " + count);
        in.close();
    }
}