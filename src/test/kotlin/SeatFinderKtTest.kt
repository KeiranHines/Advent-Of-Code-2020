import org.testng.Assert
import org.testng.annotations.Test

class SeatFinderKtTest {
    @Test
    fun seatNumberFinderExample1()
    {
        Assert.assertEquals(findSeatId("FBFBBFFRLR", 0,127, 0, 7), 357)
    }
}