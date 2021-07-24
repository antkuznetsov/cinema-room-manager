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

    public static void main(String[] args) {
        new Cinema(7, 8);
    }

    public Cinema(int rows, int seats) {
        initHall();
        printHallPlan();
        //readData();
        checkPriceAndBookSeat();
        printHallPlan();
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
}
