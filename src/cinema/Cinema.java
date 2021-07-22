package cinema;

public class Cinema {
    private final String [][] hall;

    public static void main(String[] args) {
        Cinema app = new Cinema(7, 8);
        app.printHallPlan();
    }

    public Cinema(int rows, int seats) {
        hall = new String[rows][seats];
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
}
