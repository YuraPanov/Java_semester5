import java.util.Scanner;

public class task_4 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        System.out.print("\ntask_4\nInput count: ");
        int pathCount = in.nextInt();
        int pathID = -1;
        int maxHeight = 0;

        for (int i = 0; i < pathCount; i++) {
            System.out.print("Введите количество туннелей для дороги " + (i + 1) + ": ");
            int tunnelCount = in.nextInt();
            int minPathHeight = Integer.MAX_VALUE;

            for (int j = 0; j < tunnelCount; j++) {
                System.out.print("Введите высоту туннеля " + (j + 1) + ": ");
                int currentHeight = in.nextInt();
                minPathHeight = Integer.min(currentHeight, minPathHeight);
            }

            if (minPathHeight > maxHeight) {
                maxHeight = minPathHeight;
                pathID = i + 1;
            }
        }
        System.out.println("Дорога с максимальной высотой: " + pathID + ", Высота: " + maxHeight);
        in.close();
    }
}