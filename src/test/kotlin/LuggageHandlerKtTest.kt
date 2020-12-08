import org.testng.Assert
import org.testng.annotations.Test

/**
 * TODO Add class/file description
 *
 * @author
 * @creation 8/12/2020
 */
class LuggageHandlerKtTest {

    @Test
    fun testLuggageRequirements()
    {
        val input = example.split('\n')
        val bagMapping = parseLuggageRequirements(input)
        val options = findValidBagOptions(bagMapping, "shiny gold")
        Assert.assertEquals(options.size, 4)
    }


    @Test
    fun testLuggageBagCount() {
        val input = example2.split('\n')
        val bagMapping = parseLuggageRequirements(input)
        Assert.assertEquals(countInnerBags(bagMapping, "shiny gold"), 126)
    }

    val example = """light red bags contain 1 bright white bag, 2 muted yellow bags.
dark orange bags contain 3 bright white bags, 4 muted yellow bags.
bright white bags contain 1 shiny gold bag.
muted yellow bags contain 2 shiny gold bags, 9 faded blue bags.
shiny gold bags contain 1 dark olive bag, 2 vibrant plum bags.
dark olive bags contain 3 faded blue bags, 4 dotted black bags.
vibrant plum bags contain 5 faded blue bags, 6 dotted black bags.
faded blue bags contain no other bags.
dotted black bags contain no other bags."""
}

    val example2 = """shiny gold bags contain 2 dark red bags.
dark red bags contain 2 dark orange bags.
dark orange bags contain 2 dark yellow bags.
dark yellow bags contain 2 dark green bags.
dark green bags contain 2 dark blue bags.
dark blue bags contain 2 dark violet bags.
dark violet bags contain no other bags."""