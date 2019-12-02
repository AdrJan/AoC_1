import org.testng.Assert;
import org.testng.annotations.Test;

public class UnitTests {

    @Test
    public void test_12() {
        Assert.assertEquals(Solution.getFuelForMass(12), 2);
    }

    @Test
    public void test_14() {
        Assert.assertEquals(Solution.getFuelForMass(14), 2);
    }

    @Test
    public void test_1969() {
        Assert.assertEquals(Solution.getFuelForMass(1969), 654);
    }

    @Test
    public void test_100756() {
        Assert.assertEquals(Solution.getFuelForMass(100756), 33583);
    }
}
