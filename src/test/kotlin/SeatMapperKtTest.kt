import org.testng.Assert
import org.testng.annotations.Test

class SeatMapperKtTest {

    @Test
    fun testInitialSimulation() {
        val input = exampleMap.split('\n')
        val result = runSimulation(input)
        val stillEmpty = result.any { it.contains('L') }
        Assert.assertFalse(stillEmpty)
    }

    @Test
    fun testSimulation() {
        val input = exampleMap.split('\n')
        val result = runSimulationTillComplete(input)
        val count = result.map { it.count { it == '#' } }.sum()
        Assert.assertEquals(count, 37)
    }

    @Test
    fun testInitialAdvancedSimulation() {
        val input = exampleMap.split('\n')
        val result = runAdvancedSimulation(input)
        val stillEmpty = result.any { it.contains('L') }
        Assert.assertFalse(stillEmpty)
        Assert.assertEquals(result, round1Result.split('\n'))
    }

    @Test
    fun testAdvancedSimulationRound2() {
        val input = round1Result.split('\n')
        val round2 = runAdvancedSimulation(input)
        Assert.assertEquals(round2, round2Result.split('\n'))
    }


    @Test
    fun testAdvancedSimulationRound3() {
        val round2 = round2Result.split('\n')
        val round3 = runAdvancedSimulation(round2)
        round3.forEach(::println)
        Assert.assertEquals(round3, round3Result.split('\n'))
    }

    @Test
    fun testAdvancedSimulation() {
        val input = exampleMap.split('\n')
        val result = runAdvancedSimulationTillComplete(input)
        val count = result.map { it.count { it == '#' } }.sum()
        Assert.assertEquals(count, 26)
    }

    val exampleMap = """L.LL.LL.LL
LLLLLLL.LL
L.L.L..L..
LLLL.LL.LL
L.LL.LL.LL
L.LLLLL.LL
..L.L.....
LLLLLLLLLL
L.LLLLLL.L
L.LLLLL.LL"""
}

val round1Result = """#.##.##.##
#######.##
#.#.#..#..
####.##.##
#.##.##.##
#.#####.##
..#.#.....
##########
#.######.#
#.#####.##"""

val round2Result = """#.LL.LL.L#
#LLLLLL.LL
L.L.L..L..
LLLL.LL.LL
L.LL.LL.LL
L.LLLLL.LL
..L.L.....
LLLLLLLLL#
#.LLLLLL.L
#.LLLLL.L#"""

val round3Result = """#.L#.##.L#
#L#####.LL
L.#.#..#..
##L#.##.##
#.##.#L.##
#.#####.#L
..#.#.....
LLL####LL#
#.L#####.L
#.L####.L#"""