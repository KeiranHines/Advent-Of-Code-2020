import kotlin.Pair

fun findTwoNumbersForSum(list: List<Int>, sum: Int): Pair<Int, Int>? {
    list.forEach {
        val diff = sum - it
        if (list.contains(diff)) {
            return Pair(it, diff)
        }
    }
    return null;
}

fun findThreeNumbersForSum(list: List<Int>, sum: Int): Triple<Int, Int, Int>? {
    list.forEach {
        val diff = sum - it
        val diffPair = findTwoNumbersForSum(list, diff)
        if (diffPair != null) {
            return Triple(diffPair.first, diffPair.second, it)
        }
    }
    return null
}
