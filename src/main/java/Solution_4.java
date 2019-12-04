public class Solution_4 {

    private static final String ANSWER_FORMAT = "Answer part %s: %s";

    public static void main(String... args) {
        Solution_4 solution = new Solution_4();

        int start = 264360;
        int end = 746325;
        int counter = 0;

        for(int i = start; i <= end; i++) {
            if(solution.isMeetingCriteriaPartOne(i)) {
                counter++;
            }
        }

        System.out.println(String.format(ANSWER_FORMAT, "one", counter));

        counter = 0;

        for(int i = start; i <= end; i++) {
            if(solution.isMeetingCriteriaPartTwo(i)) {
                counter++;
            }
        }

        System.out.println(String.format(ANSWER_FORMAT, "two", counter));
    }

    boolean isMeetingCriteriaPartOne(int number) {
        String numberStr = String.valueOf(number);

        if(numberStr.length() != 6) return false;

        boolean hasTwoSameDigitsNextToEachOther = false;

        for(int i = 0; i < numberStr.length() - 1; i++) {
            int count = howManyAdjacent(numberStr, i);
            if(count > 1) {
                hasTwoSameDigitsNextToEachOther = true;
                break;
            }
        }

        return hasTwoSameDigitsNextToEachOther && !isIncreasing(number);
    }

    boolean isMeetingCriteriaPartTwo(int number) {
        String numberStr = String.valueOf(number);

        if(numberStr.length() != 6) return false;

        boolean hasTwoSameDigitsNextToEachOther = false;

        for(int i = 0; i < numberStr.length() - 1; i++) {
            int count = howManyAdjacent(numberStr, i);
            if(count > 2) {
                i += count - 1;
            } else if(count == 2) {
                hasTwoSameDigitsNextToEachOther = true;
            }
        }

        return hasTwoSameDigitsNextToEachOther && !isIncreasing(number);
    }

    private int howManyAdjacent(String numberStr, int index) {
        char c = numberStr.charAt(index);
        int count = 1;

        for(int i = index + 1; i < numberStr.length(); i++) {
            if(numberStr.charAt(i) == c) {
                count++;
            } else {
                break;
            }
        }

        return count;
    }

    private boolean isIncreasing(int number) {
        String numberStr = String.valueOf(number);
        boolean isIncreasing = false;

        for(int i = 0; i < numberStr.length() - 1; i++) {
            if(numberStr.charAt(i) > numberStr.charAt(i + 1)) {
                isIncreasing = true;
                break;
            }
        }

        return isIncreasing;
    }
}
