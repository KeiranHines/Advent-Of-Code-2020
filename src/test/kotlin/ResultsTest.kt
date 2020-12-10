import org.testng.Assert
import org.testng.annotations.Test


/**
 * Tests to get all the Challenge results
 *
 * @author Keiran
 * @creation 1/12/2020
 */
class ResultsTest {

    @Test
    fun day1Part1() {
        val list = utils.readLinesFromFile("day1Input.txt").map(String::toInt)
        val pair = findTwoNumbersForSum(list, 2020);
        Assert.assertNotNull(pair)
        if (pair != null) {
            Assert.assertEquals((pair.first * pair.second), 1015476)
        }
    }

    @Test
    fun day1Part2() {
        val list = utils.readLinesFromFile("day1Input.txt").map(String::toInt)
        val triple = findThreeNumbersForSum(list, 2020);
        Assert.assertNotNull(triple)
        if (triple != null) {
            Assert.assertEquals((triple.first * triple.second * triple.third), 200878544)
        }
    }

    @Test
    fun day2Part1() {
        val count = utils.readLinesFromFile("day2Input.txt").count { isPasswordValid(it) }
        Assert.assertEquals(count, 456)
    }

    @Test
    fun day2Part2() {
        val count = utils.readLinesFromFile("day2Input.txt").count { isPasswordCorrect(it) }
        Assert.assertEquals(count, 308)
    }

    @Test
    fun day3Part1() {
        val map = utils.readLinesFromFile("day3Input.txt")
        Assert.assertEquals(numTreesInStraightPath(map), 257)
    }

    @Test
    fun day3Part2() {
        val map = utils.readLinesFromFile("day3Input.txt")
        val totalRoutes = numTreesInStraightPath(map, 1, 1) *
                numTreesInStraightPath(map, 3, 1) *
                numTreesInStraightPath(map, 5, 1) *
                numTreesInStraightPath(map, 7, 1) *
                numTreesInStraightPath(map, 1, 2)
        Assert.assertEquals(totalRoutes, 1744787392)
    }

    @Test
    fun day4Part1() {
        val data = utils.readLinesFromFile("day4Input.txt")
        val passports = parsePassportEntries(data)
        Assert.assertEquals(passports.count { passportHasRequiredFields(it) }, 260)
    }

    @Test
    fun day4Part2() {
        val data = utils.readLinesFromFile("day4Input.txt")
        val passports = parsePassportEntries(data).filter { passportHasRequiredFields(it) }
        Assert.assertEquals(passports.count { isPassportValid(it) }, 153)
    }

    @Test
    fun day5Part1() {
        val data = utils.readLinesFromFile("day5Input.txt")
        Assert.assertEquals(data.maxOf { findSeatId(it) }, 894)
    }

    @Test
    fun day5Part2()
    {
        val data = utils.readLinesFromFile("day5Input.txt")
        val listOfSeats = mutableListOf<Int>()
        val possible = data.map { findSeatId(it) }
            .toCollection(listOfSeats)
            .filter { !listOfSeats.containsAll(listOf(it -1, it + 1)) && listOfSeats.containsAll(listOf(it + 2,it - 2)) }
            .sum()
            .div(2)
        Assert.assertEquals(possible, 579)
    }

    @Test
    fun day6Part1() {
        val data = utils.readLinesFromFile("day6Input.txt")
        Assert.assertEquals(countUniqueDeclarations(data), 6885)
    }

    @Test
    fun day6Part2() {
        val data = utils.readLinesFromFile("day6Input.txt")
        Assert.assertEquals(countCollectiveDeclarations(data), 3550)
    }

    @Test
    fun day7Part1() {
        val input = utils.readLinesFromFile("day7Input.txt")
        val bagMapping = parseLuggageRequirements(input)
        val options = findValidBagOptions(bagMapping, "shiny gold")
        Assert.assertEquals(options.size, 378)
    }

    @Test
    fun day7Part2() {
        val input = utils.readLinesFromFile("day7Input.txt")
        val bagMapping = parseLuggageRequirements(input)
        Assert.assertEquals(countInnerBags(bagMapping, "shiny gold"), 27526)
    }

    @Test
    fun day8Part1() {
        val input = utils.readLinesFromFile("day8Input.txt")
        val c = ConsoleComputer()
        c.runProgramTillRepeat(input)
        Assert.assertEquals(c.accumulator, 1709)
    }

    @Test
    fun day8Part2() {
        val c = ConsoleComputer()
        val input = utils.readLinesFromFile("day8Input.txt")
        val safe = c.fixBug(input)
        c.runProgramExit(safe)
        Assert.assertEquals(c.accumulator, 1976)
    }


    @Test
    fun day9Part1() {
        val input = utils.readLinesFromFile("day9Input.txt").map { it.toLong() }
        val result = findFirstInvalidNumber(input)
        Assert.assertEquals(result, 552655238)
    }

    @Test
    fun day9Part2() {
        val input = utils.readLinesFromFile("day9Input.txt").map { it.toLong() }
        val invalid = findFirstInvalidNumber(input)
        val sum = findRangeToSum(input, invalid).toList().sum()
        Assert.assertEquals(sum, 70672245)
    }

    @Test
    fun day10Part1() {
        val input = utils.readLinesFromFile("day10Input.txt").map { it.toInt() }
        val changes = findJoltageChanges(input)
        Assert.assertEquals(changes.first * changes.third, 1)
    }

    @Test
    fun day10Part2() {
        val input = utils.readLinesFromFile("day10Input.txt").map { it.toInt() }
        val combinations = findJoltageCombinations(input)
        Assert.assertEquals(combinations, 12089663946752)
    }
}