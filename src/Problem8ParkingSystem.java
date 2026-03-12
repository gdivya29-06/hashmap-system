import java.util.Arrays;

public class Problem8ParkingSystem {

    static String[] parking = new String[500];

    public static int hash(String plate) {
        return Math.abs(plate.hashCode()) % parking.length;
    }

    public static void parkVehicle(String plate) {

        int spot = hash(plate);

        while (parking[spot] != null) {
            spot = (spot + 1) % parking.length;
        }

        parking[spot] = plate;

        System.out.println("Vehicle " + plate + " parked at spot " + spot);
    }

    public static void exitVehicle(String plate) {

        for (int i = 0; i < parking.length; i++) {

            if (plate.equals(parking[i])) {
                parking[i] = null;
                System.out.println("Vehicle exited from spot " + i);
                return;
            }
        }

        System.out.println("Vehicle not found");
    }

    public static void main(String[] args) {

        parkVehicle("ABC1234");
        parkVehicle("XYZ9999");

        exitVehicle("ABC1234");

    }
}
