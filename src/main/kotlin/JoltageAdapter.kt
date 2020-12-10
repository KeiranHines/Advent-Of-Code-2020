import kotlin.math.min

fun findJoltageChanges(adapters: List<Int>): Triple<Int, Int, Int> {
    val copy = adapters.toMutableList()
    copy.add(0) // Initial is 0 Jolts
    val sorted = copy.sorted()
    val deltas = sorted.mapIndexed { index, it ->
        if (index == sorted.size - 1) {
            3 // Max + 3 as per spec
        } else {
            (sorted[index + 1] - it)
        }
    }
    return Triple(deltas.count { it == 1 }, deltas.count { it == 2 }, deltas.count { it == 3 })
}

fun findJoltageCombinations(adapters: List<Int>): Number {
    val sorted = adapters.sorted()
    val options = listOf(0)
    return getOptions(sorted, options)
}

private fun getOptions(
    sortedList: List<Int>,
    options: List<Int>,
    target: Int = sortedList.maxOrNull()!!,
    cache: MutableMap<Int, Long> = mutableMapOf()
): Long {
    var count: Long = 0
    options.forEach {
        if (it in cache) {
            count += cache[it]!!
        } else {
            val index = sortedList.indexOf(it)
            val indexLimit = min(index + 4, sortedList.size)
            val limit = it + 3
            val sublist = sortedList.subList(index + 1, indexLimit).filter { it <= limit }
            val new = getOptions(sortedList, sublist, target, cache)
            if (sublist.contains(target)) {
                cache[it] = new + 1
                count = new + 1
            } else {
                cache[it] = new
                count += new
            }
        }
    }
    return count
}