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

    private int rowsNeeded;
    private int seatsNeeded;

    private enum Action {
        SHOW_SEATS("1"),
        BUY_TICKET("2"),
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
        //readData();
        //calculatePrice();
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
    }

    private void showMenu() {
        System.out.println("1. Show the seats");
        System.out.println("2. Buy a ticket");
        System.out.println("0. Exit");
    }

    private void performAction(Action action) {
        switch (action) {
            case SHOW_SEATS:
                printHallPlan();
                break;
            case BUY_TICKET:
                checkPriceAndBookSeat();
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

    private void readData() {
        System.out.println("Enter the number of rows:");
        rowsNeeded = Integer.parseInt(scanner.nextLine());
        System.out.println("Enter the number of seats in each row:");
        seatsNeeded = Integer.parseInt(scanner.nextLine());
    }

    private void calculatePrice() {
        int placesNeeded = rowsNeeded * seatsNeeded;
        int resultPrice;
        if (placesNeeded <= SIZE_OF_SMALL_HALL) {
            resultPrice = rowsNeeded * seatsNeeded * PRICE;
        } else {
            int middleRow = rowsNeeded / 2;
            int priceOfFirstHalf = middleRow * seatsNeeded * PRICE;
            int priceOfSecondHalf = (rowsNeeded - middleRow) * seatsNeeded * DISCOUNT_PRICE;
            resultPrice = priceOfFirstHalf + priceOfSecondHalf;
        }
        System.out.println("Total income:");
        System.out.printf("$%d%n", resultPrice);
    }

    private void checkPriceAndBookSeat() {
        System.out.println("Enter a row number:");
        int rowNumber = Integer.parseInt(scanner.nextLine());
        System.out.println("Enter a seat number in that row:");
        int seatNumber = Integer.parseInt(scanner.nextLine());

        bookSeat(rowNumber, seatNumber);

        int resultPrice;
        if (capacity <= SIZE_OF_SMALL_HALL) {
            resultPrice = PRICE;
        } else {
            int middleRow = rows / 2;
            if (rowNumber <= middleRow) {
                resultPrice = PRICE;
            } else {
                resultPrice = DISCOUNT_PRICE;
            }
        }
        System.out.printf("Ticket price: $%d%n", resultPrice);
    }

    private void bookSeat(int row, int seat) {
        hall[row - 1][seat - 1] = 'B';
    }

    private void exit() {
        System.exit(0);
    }
}
