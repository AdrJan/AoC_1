import java.io.File;
import java.util.*;

public class Solution_3 {

    //TODO: rethink idea
    //Quite bad and uneffective solution

    public static void main(String... args) {
        File file = new File("./src/main/resources/input_3.txt");
        ArrayList<Coordinate> firstPathCoords = new ArrayList<>();

        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                String[] instructions = scanner.nextLine().split(",");
                saveAndCheckPath(firstPathCoords, instructions, true);
                instructions = scanner.nextLine().split(",");
                saveAndCheckPath(firstPathCoords, instructions, false);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void saveAndCheckPath(ArrayList<Coordinate> coordinates, String[] instructions, boolean isFirstPath) {
        int pathLength = 0;
        int x = 0;
        int y = 0;
        int minPathManh = 999999999;
        int minPath = 999999999;

        for (String instr : instructions) {
            for (int j = 0; j < Integer.parseInt(instr.substring(1)); j++) {
                switch (instr.charAt(0)) {
                    case 'R':
                        ++x;
                        break;
                    case 'L':
                        --x;
                        break;
                    case 'U':
                        ++y;
                        break;
                    case 'D':
                        --y;
                        break;
                    default:
                        throw new IllegalArgumentException();
                }
                ++pathLength;
                Coordinate newCoord = new Coordinate(x, y, pathLength);

                if (isFirstPath) {
                    coordinates.add(newCoord);
                } else if (coordinates.contains(newCoord)) {
                    int manhMinLength = Math.abs(x) + Math.abs(y);
                    if (manhMinLength < minPathManh) {
                        minPathManh = manhMinLength;
                    }
                    int firstPathLength = coordinates.get(coordinates.indexOf(newCoord)).length;

                    if (pathLength + firstPathLength < minPath) {
                        minPath = pathLength + firstPathLength;
                    }
                }
            }
        }
        if (!isFirstPath) {
            final String ANSWER_FORMAT = "Part %s answer: %s";

            System.out.println(String.format(ANSWER_FORMAT, "one", minPathManh));
            System.out.println(String.format(ANSWER_FORMAT, "two", minPath));
        }
    }
}

class Coordinate {
    private final int x;
    private final int y;
    final int length;

    Coordinate(int x, int y, int length) {
        this.x = x;
        this.y = y;
        this.length = length;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Coordinate)) return false;
        Coordinate otherCoord = (Coordinate) obj;
        return otherCoord.x == this.x && otherCoord.y == this.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.x, this.y);
    }
}