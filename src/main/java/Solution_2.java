import java.util.ArrayList;

public class Solution_2 {

    public static void main(String... args) {

    }

    int getIntcodeFirstValue(ArrayList<Integer> values) {
        for(int i = 0; i < values.size(); i++) {
            switch (values.get(i)) {
                case 1:
                    values.set(
                            values.get(i + 3),
                            values.get(values.get(i + 1)) + values.get(values.get(i + 2))
                    );
                    break;
                case 2:
                    values.set(
                            values.get(i + 3),
                            values.get(values.get(i + 1)) * values.get(values.get(i + 2))
                    );
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
