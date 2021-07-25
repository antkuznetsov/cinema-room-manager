package cinema;

import java.util.*;

public class Cinema {
    private final Scanner scanner = new Scanner(System.in);
    private final int PRICE = 10;
    private final int DISCOUNT_PRICE = 8;
    private final int SIZE_OF_SMALL_HALL = 60;

    private int rows;
    private int seats;
    private int capacity;
    private char[][] hall;

    private int purchasedTickets = 0;
    private int currentIncome = 0;
    private int totalIncome;

    private enum Action {
        SHOW_SEATS("1"),
        BUY_TICKET("2"),
        STATISTICS("3"),
        EXIT("0"),
        UNKNOWN("-1");

        String value;

        Action(String value) {
            this.value = value;
        }

        public static Action get(String code) {
            for (Action action: Action.values()) {
                if (code.equals(action.value)) {
                    return action;
                }
            }
            return UNKNOWN;
        }
    }

    public static void main(String[] args) {
        new Cinema();
    }

    public Cinema() {
        initHall();
        while (true) {
            showMenu();
            Action action = Action.get(scanner.nextLine());
            performAction(action);
        }
    }

    private void initHall() {
        System.out.println("Enter the number of rows:");
        rows = Integer.parseInt(scanner.nextLine());

        System.out.println("Enter the number of seats in each row:");
        seats = Integer.parseInt(scanner.nextLine());

        capacity = rows * seats;
        hall = new char[rows][seats];
        for (int i = 0; i < hall.length; i++) {
            for (int j = 0; j < hall[i].length; j++) {
                hall[i][j] = 'S';
            }
        }

        totalIncome = calculateTotalIncome();
    }

    private void showMenu() {
        System.out.println("1. Show the seats");
        System.out.println("2. Buy a ticket");
        System.out.println("3. Statistics");
        System.out.println("0. Exit");
    }

    private void performAction(Action action) {
        switch (action) {
            case SHOW_SEATS:
                printHallPlan();
                break;
            case BUY_TICKET:
                buyTicket();
                break;
            case STATISTICS:
                printStatistics();
                break;
            case EXIT:
                exit();
                break;
            default:
                break;
        }
    }

    private void printHallPlan() {
        System.out.println("Cinema:");
        System.out.print("  ");
        for (int i = 0; i < hall[0].length; i++) {
            System.out.printf("%s ", i + 1);
        }
        System.out.println();
        for (int i = 0; i < hall.length; i++) {
            System.out.printf("%d ", i + 1);
            for (int j = 0; j < hall[i].length; j++) {
                System.out.printf("%s ", hall[i][j]);
            }
            System.out.println();
        }
    }

    private int calculateTotalIncome() {
        int income;
        if (capacity <= SIZE_OF_SMALL_HALL) {
            income = rows * seats * PRICE;
        } else {
            int middleRow = rows / 2;
            int priceOfFirstHalf = middleRow * seats * PRICE;
            int priceOfSecondHalf = (rows - middleRow) * seats * DISCOUNT_PRICE;
            income = priceOfFirstHalf + priceOfSecondHalf;
        }
        return income;
    }

    private void buyTicket() {
        int[] input = readData();

        while (!isDataValid(input)) {
            System.out.println("Wrong input!");
            input = readData();
        }

        while (!isSeatFree(input)) {
            System.out.println("That ticket has already been purchased!");
            input = readData();
        }

        bookSeat(input);

        int price = calculatePrice(input[0]);
        currentIncome += price;

        System.out.println("Ticket price: " + formatPrice(price));
    }

    private int[] readData() {
        int[] input = new int[2];
        System.out.println("Enter a row number:");
        input[0] = Integer.parseInt(scanner.nextLine());
        System.out.println("Enter a seat number in that row:");
        input[1] = Integer.parseInt(scanner.nextLine());
        return input;
    }

    private boolean isSeatFree(int[] input) {
        int i = input[0] - 1;
        int j = input[1] - 1;

        return hall[i][j] == 'S';
    }

    private boolean isDataValid(int[] input) {
        int i = input[0] - 1;
        int j = input[1] - 1;

        boolean isRowValid = i >= 0 && i < hall.length;
        boolean isSeatValid = j >= 0 && j < hall[0].length;

        return isRowValid && isSeatValid;
    }

    private void bookSeat(int[] input) {
        int i = input[0] - 1;
        int j = input[1] - 1;

        hall[i][j] = 'B';
        purchasedTickets++;
    }

    private int calculatePrice(int rowNumber) {
        int price;
        if (capacity <= SIZE_OF_SMALL_HALL) {
            price = PRICE;
        } else {
            int middleRow = rows / 2;
            if (rowNumber <= middleRow) {
                price = PRICE;
            } else {
                price = DISCOUNT_PRICE;
            }
        }
        return price;
    }

    private void printStatistics() {
        System.out.println("Number of purchased tickets: " + getNumberOfPurchasedTickets());
        System.out.printf("Percentage: %.2f%%%n", getPercentage());
        System.out.println("Current income: " + formatPrice(getCurrentIncome()));
        System.out.println("Total income: " + formatPrice(getTotalIncome()));
    }

    private int getNumberOfPurchasedTickets() {
        return purchasedTickets;
    }

    private double getPercentage() {
        return (double) purchasedTickets / capacity * 100.0;
    }

    private int getCurrentIncome() {
        return currentIncome;
    }

    private int getTotalIncome() {
        return totalIncome;
    }

    private String formatPrice(int price) {
        return String.format("$%d", price);
    }

    private void exit() {
        System.exit(0);
    }
}
