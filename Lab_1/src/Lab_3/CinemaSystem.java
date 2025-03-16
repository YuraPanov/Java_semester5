package Lab_3;

import java.util.*;

// Полировать есть что(добавить проверки на совпадения и тайминг начала конца сеагсов)

class Seat {
    boolean isBooked;

    Seat() {
        this.isBooked = false;
    }

    void book() {
        this.isBooked = true;
    }
    void unbook() {
        this.isBooked = false;
    }
}

class Hall {
    String name;
    Seat[][] seats;
    List<Session> sessions = new ArrayList<>();

    Hall(String name, int rows, int cols) {
        this.name = name;
        seats = new Seat[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                seats[i][j] = new Seat();
            }
        }
    }

    void addSession(Session session) {
        sessions.add(session);
    }
}

class Cinema {
    String name;
    List<Hall> halls = new ArrayList<>();

    Cinema(String name) {
        this.name = name;
    }

    void addHall(Hall hall) {
        halls.add(hall);
    }
}

class Session {
    String movie;
    String time;
    Hall hall;

    Session(String movie, String time, Hall hall) {
        this.movie = movie;
        this.time = time;
        this.hall = hall;
    }
}

public class CinemaSystem {
    static List<Cinema> cinemas = new ArrayList<>();
    static Scanner scanner = new Scanner(System.in);
    static final String ADMIN_LOGIN = "admin";
    static final String ADMIN_PASSWORD = "1234";


    public static void main(String[] args) {
        while (true) {
            System.out.println("1. Меню админестратора\n2. Покупка билетов\n10. Выход");
            if (!scanner.hasNextInt()) {
                System.out.println("Повторите запрос");
                scanner.next();
                continue;
            }
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1 -> adminMenu();
                case 2 -> ticketMenu();
                case 10 -> System.exit(0);
                default -> System.out.println("Неверный ввод, попробуйте снова.");
            }
        }
    }

    static void adminMenu(){
        System.out.println("Введите логин:");
        String login = scanner.nextLine();
        System.out.println("Введите пароль:");
        String password = scanner.nextLine();
        if (!login.equals(ADMIN_LOGIN)|| !password.equals(ADMIN_PASSWORD)){
            System.out.println("Неверный логин или пароль.");
            return;
        }
        System.out.println("Добро пожаловать, Товарищь Администратор!");
        while (true){
            System.out.println("1. Добавить кинотеатр\n2. Добавить зал\n3. Создать сеанс\n10. Выход");
            if (!scanner.hasNextInt()){
                System.out.println("Повторите запрос");
                scanner.next();
                continue;
            }
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice){
                case 1 -> addCinema();
                case 2 -> addHall();
                case 3 -> createSession();
                case 10 -> {
                    return;
                }
                default -> System.out.println("Неверный ввод, попробуйте снова.");
            }
        }
    }

    static void ticketMenu() {
        while (true) {
            System.out.println("1. Купить билет\n2. Вернуться в главное меню");
            if (!scanner.hasNextInt()) {
                System.out.println("Повторите запрос");
                scanner.next();
                continue;
            }
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1 -> bookTicket();
                case 2 -> {
                    return;
                }
                default -> System.out.println("Неверный ввод, попробуйте снова.");
            }
        }
    }

    static void addCinema() {
        System.out.println("Введите название кинотеатра (для выхода введите ESC):");
        String name = scanner.nextLine();
        if (name.equalsIgnoreCase("ESC")) {
            return;
        }
        if (name.trim().isEmpty()) {
            System.out.println("Название кинотеатра не может быть пустым.");
            return;
        }
        cinemas.add(new Cinema(name));
        System.out.println("Кинотеатр добавлен.");
    }

    static void addHall() {
        if (cinemas.isEmpty()) {
            System.out.println("Нет кинотеатров. Сначала добавьте кинотеатр.");
            return;
        }

        System.out.println("Выберите кинотеатр (для выхода введите ESC):");
        for (int i = 0; i < cinemas.size(); i++) {
            System.out.println((i + 1) + ". " + cinemas.get(i).name);
        }

        String input = scanner.nextLine();
        if (input.equalsIgnoreCase("ESC")) {
            System.out.println("Выход из добавления зала.");
            return;
        }

        int cinemaIndex;
        try {
            cinemaIndex = Integer.parseInt(input) - 1;
        } catch (NumberFormatException e) {
            System.out.println("Неверный ввод. Пожалуйста, введите число.");
            return;
        }

        if (cinemaIndex < 0 || cinemaIndex >= cinemas.size()) {
            System.out.println("Неверный выбор кинотеатра.");
            return;
        }

        System.out.println("Введите название зала (для выхода введите ESC): ");
        String name = scanner.nextLine();
        if (name.equalsIgnoreCase("ESC")) {
            System.out.println("Выход из добавления зала.");
            return;
        }

        System.out.println("Введите количество рядов (для выхода введите ESC): ");
        String rowsInput = scanner.nextLine();
        if (rowsInput.equalsIgnoreCase("ESC")) {
            System.out.println("Выход из добавления зала.");
            return;
        }

        int rows;
        try {
            rows = Integer.parseInt(rowsInput);
        } catch (NumberFormatException e) {
            System.out.println("Неверный ввод. Пожалуйста, введите число.");
            return;
        }

        if (rows <= 0) {
            System.out.println("Количество рядов должно быть больше нуля.");
            return;
        }

        System.out.println("Введите количество мест в ряду (для выхода введите ESC): ");
        String colsInput = scanner.nextLine();
        if (colsInput.equalsIgnoreCase("ESC")) {
            System.out.println("Выход из добавления зала.");
            return;
        }

        int cols;
        try {
            cols = Integer.parseInt(colsInput);
        } catch (NumberFormatException e) {
            System.out.println("Неверный ввод. Пожалуйста, введите число.");
            return;
        }

        if (cols <= 0) {
            System.out.println("Количество мест в ряду должно быть больше нуля.");
            return;
        }

        cinemas.get(cinemaIndex).addHall(new Hall(name, rows, cols));
        System.out.println("Зал добавлен.");
    }

    static void createSession() {
        if (cinemas.isEmpty()) {
            System.out.println("Нет кинотеатров. Сначала добавьте кинотеатр.");
            return;
        }

        System.out.println("Выберите кинотеатр (для выхода введите ESC):");
        for (int i = 0; i < cinemas.size(); i++) {
            System.out.println((i + 1) + ". " + cinemas.get(i).name);
        }

        String input = scanner.nextLine();
        if (input.equalsIgnoreCase("ESC")) {
            System.out.println("Выход из создания сеанса.");
            return;
        }

        int cinemaIndex;
        try {
            cinemaIndex = Integer.parseInt(input) - 1;
        } catch (NumberFormatException e) {
            System.out.println("Неверный ввод. Пожалуйста, введите число.");
            return;
        }

        if (cinemaIndex < 0 || cinemaIndex >= cinemas.size()) {
            System.out.println("Неверный выбор кинотеатра.");
            return;
        }

        Cinema selectedCinema = cinemas.get(cinemaIndex);
        if (selectedCinema.halls.isEmpty()) {
            System.out.println("Нет залов в выбранном кинотеатре. Сначала добавьте зал.");
            return;
        }

        System.out.println("Выберите зал (для выхода введите ESC):");
        for (int i = 0; i < selectedCinema.halls.size(); i++) {
            System.out.println((i + 1) + ". " + selectedCinema.halls.get(i).name);
        }

        input = scanner.nextLine();
        if (input.equalsIgnoreCase("ESC")) {
            System.out.println("Выход из создания сеанса.");
            return;
        }

        int hallIndex;
        try {
            hallIndex = Integer.parseInt(input) - 1;
        } catch (NumberFormatException e) {
            System.out.println("Неверный ввод. Пожалуйста, введите число.");
            return;
        }

        if (hallIndex < 0 || hallIndex >= selectedCinema.halls.size()) {
            System.out.println("Неверный выбор зала.");
            return;
        }

        Hall selectedHall = selectedCinema.halls.get(hallIndex);

        System.out.println("Введите название фильма (для выхода введите ESC): ");
        String movie = scanner.nextLine();
        if (movie.equalsIgnoreCase("ESC")) {
            System.out.println("Выход из создания сеанса.");
            return;
        }

        System.out.println("Введите время сеанса (для выхода введите ESC): ");
        String time = scanner.nextLine();
        if (time.equalsIgnoreCase("ESC")) {
            System.out.println("Выход из создания сеанса.");
            return;
        }

        selectedHall.addSession(new Session(movie, time, selectedHall));
        System.out.println("Сеанс создан.");
    }

    static void bookTicket() {
        if (cinemas.isEmpty()) {
            System.out.println("Нет кинотеатров.");
            return;
        }

        System.out.println("Выберите кинотеатр:");
        for (int i = 0; i < cinemas.size(); i++) {
            System.out.println((i + 1) + ". " + cinemas.get(i).name);
        }

        int cinemaIndex = scanner.nextInt() - 1;
        scanner.nextLine();

        if (cinemaIndex < 0 || cinemaIndex >= cinemas.size()) {
            System.out.println("Неверный выбор кинотеатра.");
            return;
        }

        if (cinemas.get(cinemaIndex).halls.isEmpty()) {
            System.out.println("Нет залов.");
            return;
        }

        System.out.println("Выберите зал:");
        for (int i = 0; i < cinemas.get(cinemaIndex).halls.size(); i++) {
            System.out.println((i + 1) + ". " + cinemas.get(cinemaIndex).halls.get(i).name);
        }
        int hallIndex = scanner.nextInt() - 1;
        scanner.nextLine();
        Cinema selectedCinema = cinemas.get(cinemaIndex);
        if (hallIndex < 0 || hallIndex >= selectedCinema.halls.size()) {
            System.out.println("Неверный выбор зала.");
            return;
        }
        Hall selectedHall = cinemas.get(cinemaIndex).halls.get(hallIndex);
        if (selectedHall.sessions.isEmpty()) {
            System.out.println("Нет сеансов.");
            return;
        }

        System.out.println("Выберите сеанс:");
        for (int i = 0; i < selectedHall.sessions.size(); i++) {
            System.out.println((i + 1) + ". " + selectedHall.sessions.get(i).movie + " " + selectedHall.sessions.get(i).time);
        }

        int sessionIndex = scanner.nextInt() - 1;
        scanner.nextLine();
        if (sessionIndex < 0 || sessionIndex >= selectedHall.sessions.size()) {
            System.out.println("Неверный выбор сеанса.");
            return;
        }

        int totalRows = selectedHall.seats.length;
        int totalSeats = selectedHall.seats[0].length;

        while (true) {
            System.out.println("В этом зале " + totalRows + " рядов и " + totalSeats + " мест в каждом ряду.");
            displaySeatMap(selectedHall, totalRows, totalSeats);

            System.out.println("1. Купить билет\n2. Отменить билет\n10. Выход");
            if (!scanner.hasNextInt()) {
                System.out.println("Повторите запрос");
                scanner.next();
                continue;
            }
            int choice = scanner.nextInt();
            switch (choice) {
                case 1 -> buy(selectedHall, totalRows, totalSeats);
                case 2 -> sell(selectedHall, totalRows, totalSeats);
                case 10 -> {
                    return;
                }
                default -> System.out.println("Неверный ввод, попробуйте снова.");
            }
        }
    }

    private static void displaySeatMap(Hall selectedHall, int totalRows, int totalSeats) {
        System.out.print("       ");
        for (int j = 1; j <= totalSeats; j++) {
            System.out.printf("%-3s", j);
        }
        System.out.println();
        for (int i = 0; i < totalRows; i++) {
            System.out.printf("Ряд %-2d: ", i + 1);
            for (int j = 0; j < totalSeats; j++) {
                String seatStatus = selectedHall.seats[i][j].isBooked ? "■" : "□";
                System.out.printf("%-3s", seatStatus);
            }
            System.out.println();
        }
    }

    private static void buy(Hall selectedHall, int totalRows, int totalSeats) {
        System.out.print("Введите ряд (1-" + totalRows + "): ");
        int row = scanner.nextInt() - 1;
        System.out.print("Введите место (1-" + totalSeats + "): ");
        int col = scanner.nextInt() - 1;
        if (row >= 0 && row < totalRows && col >= 0 && col < totalSeats) {
            if (!selectedHall.seats[row][col].isBooked) {
                selectedHall.seats[row][col].book();
                System.out.println("Билет куплен!");
            } else {
                System.out.println("Место уже занято. Выберите другое место.");
            }
        }
        else {
            System.out.println("Неверный ввод. Попробуйте снова.");
        }
    }

    private static void sell(Hall selectedHall, int totalRows,int totalSeats){
        System.out.print("Выбрите ряд и место где хотите отменить бронь:");
        System.out.print("Введите ряд (1-" + totalRows + "): ");
        int row = scanner.nextInt() - 1;
        System.out.print("Введите место (1-" + totalSeats + "): ");
        int col = scanner.nextInt() - 1;

        if (row >= 0 && row < totalRows && col >= 0 && col < totalSeats) {
            if (selectedHall.seats[row][col].isBooked) {
                selectedHall.seats[row][col].unbook();
                System.out.println("Бронирование отменено!");
            } else {
                System.out.println("Место незанято. Выберите другое место.");
            }
        }
        else {
            System.out.println("Неверный ввод. Попробуйте снова.");

        }
    }
}