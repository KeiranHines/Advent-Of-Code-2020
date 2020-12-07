import org.testng.annotations.Test

import org.testng.Assert.*

class Day1KtTest {

    @Test
    fun testFindNumbersForSumFindsResult() {
        val list = listOf(1, 3, 5)
        val result = findTwoNumbersForSum(list, 4)
        assertEquals(result, Pair(1,3))
    }

    @Test
    fun testFindNumbersForSumReturnsNull()
    {
        val list = listOf(1, 3, 5)
        val result = findTwoNumbersForSum(list, 7)
        assertEquals(result, null)
    }
}