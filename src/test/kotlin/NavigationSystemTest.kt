import org.testng.Assert
import org.testng.annotations.Test
import kotlin.math.abs

/**
 * TODO Add class/file description
 *
 * @author
 * @creation 13/12/2020
 */
class NavigationSystemTest {

    @Test
    fun example() {
        val input = example.split('\n')
        val nav = NavigationSystem()
        val result = nav.processCommands(input)
        Assert.assertEquals(abs(result.first), 17)
        Assert.assertEquals(abs(result.second), 8)
    }

    @Test
    fun example2() {
        val input = example.split('\n')
        val nav = NavigationSystem2(waypointNorth = 1, waypointEast = 10)
        val result = nav.processCommands(input)
        Assert.assertEquals(abs(result.first), 214)
        Assert.assertEquals(abs(result.second), 72)
    }

    val example = """F10
N3
F7
R90
F11"""
}