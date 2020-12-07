import org.testng.Assert
import org.testng.annotations.Test

class TabogganKtTest {

    @Test
    fun testExampleTreeCount()
    {
        val map = example.split('\n')
        Assert.assertEquals(numTreesInStraightPath(map), 7)
    }

    @Test
    fun testExampleTreeRouteMultiple() {
        val map = example.split('\n')
        val one = numTreesInStraightPath(map, 1, 1)
        Assert.assertEquals(one, 2)
        val two = numTreesInStraightPath(map, 3, 1)
        Assert.assertEquals(two, 7)
        val three = numTreesInStraightPath(map, 5, 1)
        Assert.assertEquals(three, 3)
        val four = numTreesInStraightPath(map, 7, 1)
        Assert.assertEquals(four, 4)
        val five = numTreesInStraightPath(map, 1, 2)
        Assert.assertEquals(five, 2)
        Assert.assertEquals(one * two * three * four * five, 336)
    }

    val example =
"""..##.......
#...#...#..
.#....#..#.
..#.#...#.#
.#...##..#.
..#.##.....
.#.#.#....#
.#........#
#.##...#...
#...##....#
.#..#...#.#"""
}