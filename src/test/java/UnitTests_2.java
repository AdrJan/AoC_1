import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Arrays;

public class UnitTests_2 {

    Solution_2 solution = new Solution_2();

    @Test
    public void test_1_0_0_0_99() {
        Assert.assertEquals(
                solution.getIntcodeFirstValue(new ArrayList<Integer>(Arrays.asList(1, 0, 0, 0, 99))),
                2
        );
    }

    @Test
    public void test_2_3_0_3_99() {
        Assert.assertEquals(
                solution.getIntcodeFirstValue(new ArrayList<Integer>(Arrays.asList(2, 3, 0, 3, 99))),
                2
        );
    }

    @Test
    public void test_2_4_4_5_99_0() {
        Assert.assertEquals(
                solution.getIntcodeFirstValue(new ArrayList<Integer>(Arrays.asList(2, 4, 4, 5, 99, 0))),
                2
        );
    }

    @Test
    public void test_1_1_1_4_99_5_6_0_99() {
        Assert.assertEquals(
                solution.getIntcodeFirstValue(new ArrayList<Integer>(Arrays.asList(1, 1, 1, 4, 99, 5, 6, 0, 99))),
                30
        );
    }
}
