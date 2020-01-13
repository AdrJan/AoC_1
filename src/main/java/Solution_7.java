import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Solution_7 {

    final static String AMPL = "01234";
    final static String AMPL_FEEDBACK = "98765";
    final static String ANSWER_FORMAT = "Answer part %s: %s";
    static ArrayList<String> AMPL_PERMS = new ArrayList<>();
    static ArrayList<String> AMPL_FEEDBACK_PERMS = new ArrayList<>();

    static {
        permute(AMPL, 0, AMPL.length() - 1);
        permute(AMPL_FEEDBACK, 0, AMPL_FEEDBACK.length() - 1);
    }

    public static void main(String... args) {
        File file = new File("./src/main/resources/input_7.txt");

        try (Scanner scanner = new Scanner(file)) {
            Integer[] intcode = Arrays
                    .stream(scanner.next().split(","))
                    .map(Integer::valueOf)
                    .toArray(Integer[]::new);

            System.out.println(String.format(ANSWER_FORMAT, "one", getMaxThrust(intcode.clone())));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected static int getMaxThrust(Integer[] intcode) {
        int maxThrust = 0;

        for (int i = 0; i < AMPL_PERMS.size(); i++) {
            int currThrust = 0;
            for (int j = 0; j < AMPL_PERMS.get(i).length(); j++) {
                Integer[] currIntcode = intcode.clone();
                currThrust = parseIntcode(currIntcode, Character.getNumericValue(AMPL_PERMS.get(i).charAt(j)), currThrust);
            }

            if (currThrust > maxThrust) {
                maxThrust = currThrust;
            }
        }

        return maxThrust;
    }

    private static int parseIntcode(Integer[] values, int firstInputValue, int secondInputValue) {
        int i = 0;
        boolean flagFound = false;
        boolean isSecondInput = false;

        while (true) {
            String strOpcode = String.valueOf(values[i]);
            String modes = getModesForOpcodeParams(values[i]);
            int opcode = strOpcode.length() > 1
                    ? Integer.valueOf(strOpcode.substring(strOpcode.length() - 2))
                    : Integer.valueOf(strOpcode.substring(strOpcode.length() - 1));

            int firstParamIndex = getParamIndexWithMode(i, modes, 0, values);
            int secondParamIndex = getParamIndexWithMode(i, modes, 1, values);
            int thirdParamIndex = getParamIndexWithMode(i, modes, 2, values);

            switch (opcode) {
                case 1:
                    values[thirdParamIndex] = values[firstParamIndex] + values[secondParamIndex];
                    i += 4;
                    break;
                case 2:
                    values[thirdParamIndex] = values[firstParamIndex] * values[secondParamIndex];
                    i += 4;
                    break;
                case 3:
                    if (!isSecondInput) {
                        values[firstParamIndex] = firstInputValue;
                        isSecondInput = true;
                    } else {
                        values[firstParamIndex] = secondInputValue;
                    }
                    i += 2;
                    break;
                case 4:
                    return values[firstParamIndex];
                case 5:
                    if (values[firstParamIndex] != 0) {
                        i = values[secondParamIndex];
                    } else {
                        i += 3;
                    }
                    break;
                case 6:
                    if (values[firstParamIndex] == 0) {
                        i = values[secondParamIndex];
                    } else {
                        i += 3;
                    }
                    break;
                case 7:
                    if (values[firstParamIndex] < values[secondParamIndex]) {
                        values[thirdParamIndex] = 1;
                    } else {
                        values[thirdParamIndex] = 0;
                    }
                    i += 4;
                    break;
                case 8:
                    if (values[firstParamIndex].equals(values[secondParamIndex])) {
                        values[thirdParamIndex] = 1;
                    } else {
                        values[thirdParamIndex] = 0;
                    }
                    i += 4;
                    break;
                case 99:
                    flagFound = true;
                    break;
                default:
                    break;
            }

            if (flagFound) return 0;
        }
    }

    private static String getModesForOpcodeParams(int opcode) {
        String strValue = String.valueOf(opcode);
        String modes;

        if (strValue.length() > 2) {
            modes = String.format("%5s", strValue).replace(' ', '0');
            return modes.substring(0, 3);
        } else {
            return "000";
        }
    }

    private static int getParamIndexWithMode(int paramIndex, String modes, int whichParam, Integer[] values) {
        if (paramIndex + whichParam + 1 < values.length) {
            return (modes.charAt(2 - whichParam) == '0')
                    ? values[paramIndex + whichParam + 1]
                    : paramIndex + whichParam + 1;
        } else {
            return 0;
        }
    }

    private static void permute(String str, int l, int r) {
        if (l == r)
            AMPL_PERMS.add(str);
        else {
            for (int i = l; i <= r; i++) {
                str = swap(str, l, i);
                permute(str, l + 1, r);
                str = swap(str, l, i);
            }
        }
    }

    private static String swap(String a, int i, int j) {
        char temp;
        char[] charArray = a.toCharArray();
        temp = charArray[i];
        charArray[i] = charArray[j];
        charArray[j] = temp;
        return String.valueOf(charArray);
    }
}
