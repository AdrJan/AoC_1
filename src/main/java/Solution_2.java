import java.io.File;
import java.util.*;
import java.util.stream.Collectors;

public class Solution_2 {

    final static String ANSWER_FORMAT = "Part %s answer: %s";

    public static void main(String... args) {
        File file = new File("./src/main/resources/input_2.txt");
        Solution_2 solution = new Solution_2();

        try (Scanner scanner = new Scanner(file)) {
            List<Integer> intcode = Arrays.stream(scanner.next().split(","))
                    .map(Integer::parseInt)
                    .collect(Collectors.toList());

            //Part one
            System.out.println(
                    String.format(
                            ANSWER_FORMAT,
                            "one",
                            solution.getIntcodeFirstValue(new ArrayList<>(intcode), 12, 2)
                    )
            );

            //Part two
            for (int i = 0; i < 100; i++) {
                for (int j = 0; j < 100; j++) {
                    if (solution.getIntcodeFirstValue(new ArrayList<>(intcode), i, j) == 19690720) {
                        System.out.println(String.format(ANSWER_FORMAT, "two", (100 * i + j)));
                        break;
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private int getIntcodeFirstValue(List<Integer> values, int valueAtOne, int valueAtTwo) {
        values.set(1, valueAtOne);
        values.set(2, valueAtTwo);

        return getIntcodeFirstValue(values);
    }

    int getIntcodeFirstValue(List<Integer> values) {
        for (int i = 0; i < values.size(); i++) {
            switch (values.get(i)) {
                case 1:
                    values.set(
                            values.get(i + 3),
                            values.get(values.get(i + 1)) + values.get(values.get(i + 2))
                    );
                    i += 3;
                    break;
                case 2:
                    values.set(
                            values.get(i + 3),
                            values.get(values.get(i + 1)) * values.get(values.get(i + 2))
                    );
                    i += 3;
                    break;
                case 99:
                    return values.get(0);
                default:
                    break;
            }
        }
        return values.get(0);
    }
}
