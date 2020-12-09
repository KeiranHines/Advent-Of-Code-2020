import utils.EvictingQueue
import java.util.*

fun findFirstInvalidNumber(input: List<Long>, preambleSize: Int = 25): Long {
    val preamble: Queue<Long> = EvictingQueue(preambleSize)
    preamble.addAll(input.slice(0..preambleSize))
    input.slice(preambleSize+1 until input.size).forEach {
        if (preamble.minOrNull()!! > it|| preamble.maxOrNull()!! * 2 < it) {
            return it
        }

        if (preamble.none { pre ->
                val diff = it - pre
                diff != pre && preamble.contains(diff)
            }) {
            return it
        }
        preamble.add(it)
    }
    return 1
}

fun findRangeToSum(input: List<Long>, target: Long): Pair<Long, Long>
{
    input.forEachIndexed { index, l ->
        val list = mutableListOf(l)
        var sum = l
        for (it in (index +1).until(input.size)) {
            list.add(input[it])
            sum += input[it]
            if(sum > target)
            {
                break
            }
            if(sum == target) {
                return Pair(list.minOrNull()!!, list.maxOrNull()!!)
            }
        }
    }
    return Pair(-1,-1)
}