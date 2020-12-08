fun parseLuggageRequirements(requirements: List<String>): Map<String, Map<String, Int>> {
    val bagMapping = mutableMapOf<String, Map<String, Int>>();
    requirements.forEach {
        val specs = bagTypeRegex.find(it)!!.destructured
        bagMapping[specs.component1()] = if (specs.component2() == "no other bags") {
            mapOf(Pair("empty", 0))
        } else {
            bagContentsRegex.findAll(specs.component2())
                .map { g -> g.destructured.component2() to g.destructured.component1().toInt() }.toMap()
        }
    }
    return bagMapping
}

fun findValidBagOptions(bagMap: Map<String, Map<String, Int>>, target: String): Set<String> {
    val results = bagMap.filterValues { contents -> contents.containsKey(target) }.keys.toMutableSet()
    while (true) {
        val tempSet = mutableSetOf<String>()
        results.forEach { tempSet.addAll(bagMap.filterValues { contents -> contents.containsKey(it) }.keys) }
        if (results.containsAll(tempSet)) {
            return results
        }
        results.addAll(tempSet)
    }
}

fun countInnerBags(bagMap: Map<String, Map<String, Int>>, initial: String): Int =
    bagMap.getValue(initial).map { (newBag, count) ->
        if (newBag == "empty") 0 else count + count * countInnerBags(bagMap, newBag)
    }.sum()


private val bagTypeRegex = Regex("(.*) bags contain (.*).")
private val bagContentsRegex = Regex("(\\d+) (.*?) bag")
