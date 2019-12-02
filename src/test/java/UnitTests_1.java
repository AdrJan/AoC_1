import org.testng.Assert;
import org.testng.annotations.Test;

public class UnitTests_1 {

    @Test
    public void test_12() {
        Assert.assertEquals(Solution_1.getFuelForMass(12), 2);
    }

    @Test
    public void test_14() {
        Assert.assertEquals(Solution_1.getFuelForMass(14), 2);
    }

    @Test
    public void test_1969() {
        Assert.assertEquals(Solution_1.getFuelForMass(1969), 654);
    }

    @Test
    public void test_100756() {
        Assert.assertEquals(Solution_1.getFuelForMass(100756), 33583);
    }
}
