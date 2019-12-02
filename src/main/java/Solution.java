import java.io.File;
import java.util.Scanner;

public class Solution {

    public static void main(String... args) {
        File file = new File("./src/main/resources/input.txt");

        try (Scanner scanner = new Scanner(file)) {
            int totalFuel = 0;

            while (scanner.hasNextLine()) {
                int mass = Integer.parseInt(scanner.nextLine());

                while (getFuelForMass(mass) > 0) {
                    int fuelForMass = getFuelForMass(mass);

                    totalFuel += fuelForMass;
                    mass = fuelForMass;
                }
            }

            System.out.println("Total fuel: " + totalFuel);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    static int getFuelForMass(int mass) {
        return mass / 3 - 2;
    }
}
