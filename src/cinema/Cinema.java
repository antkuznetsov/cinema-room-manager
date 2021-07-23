package cinema;

import java.util.Scanner;

public class Cinema {
    private final Scanner scanner = new Scanner(System.in);
    private final int PRICE = 10;
    private final int DISCOUNT_PRICE = 8;
    private final int SIZE_OF_SMALL_HALL = 60;

    private final int rows;
    private final int seats;
    private final int capacity;
    private final String[][] hall;

    private int rowsNeeded;
    private int seatsNeeded;

    public static void main(String[] args) {
        new Cinema(7, 8);
    }

    public Cinema(int rows, int seats) {
        this.rows = rows;
        this.seats = seats;
        this.capacity = rows * seats;
        this.hall = new String[rows][seats];

        //printHallPlan();
        readData();
        calculatePrice();
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
                System.out.print("S ");
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
}
