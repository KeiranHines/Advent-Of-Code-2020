import org.testng.Assert
import org.testng.annotations.Test

/**
 * TODO Add class/file description
 *
 * @author
 * @creation 9/12/2020
 */
class XmasDecoderKtTest
{

    @Test
    fun testFindingInvalidData()
    {
        val input = example.split('\n').map {it.toLong()}
        val result = findFirstInvalidNumber(input, 5)
        Assert.assertEquals(result, 127)
    }

    @Test
    fun testFindingSum() {
        val input = example.split('\n').map { it.toLong() }
        val result = findFirstInvalidNumber(input, 5)
        val result2 = findRangeToSum(input, result)
        Assert.assertEquals(result2.first + result2.second, 62)
    }
    val example = """35
20
15
25
47
40
62
55
65
95
102
117
150
182
127
219
299
277
309
576"""
}
