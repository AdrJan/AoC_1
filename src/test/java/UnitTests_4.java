import org.testng.Assert;
import org.testng.annotations.Test;

public class UnitTests_4 {

    Solution_4 solution = new Solution_4();

    //Part one

    @Test
    public void test_111111() {
        Assert.assertTrue(solution.isMeetingCriteriaPartOne(111111));
    }

    @Test
    public void test_223450() {
        Assert.assertFalse(solution.isMeetingCriteriaPartOne(223450));
    }

    @Test
    public void test_123789() {
        Assert.assertFalse(solution.isMeetingCriteriaPartOne(123789));
    }

    @Test
    public void test_122345() {
        Assert.assertTrue(solution.isMeetingCriteriaPartOne(122345));
    }

    //Part two

    @Test
    public void test_123444() {
        Assert.assertFalse(solution.isMeetingCriteriaPartTwo(344457));
    }

    @Test
    public void test_112233() {
        Assert.assertTrue(solution.isMeetingCriteriaPartTwo(112233));
    }

    @Test
    public void test_111122() {
        Assert.assertTrue(solution.isMeetingCriteriaPartTwo(111122));
    }
}
