fun countUniqueDeclarations(declarations: List<String>): Int {
    var answers: MutableSet<Char> = HashSet()
    var total = 0
    declarations.forEach {
        if (it.isEmpty() || it[0].isWhitespace()) {
            total += answers.size
            answers = HashSet()
        } else {
            answers.addAll(it.toCharArray().filter { c -> !c.isWhitespace() })
        }

    }
    total += answers.size
    return total
}

fun countCollectiveDeclarations(declarations: List<String>): Int {
    var answers: MutableMap<Char, Int> = HashMap()
    var total = 0
    var groupSize = 0
    declarations.forEach {
        if (it.isEmpty() || it[0].isWhitespace()) {
            total += answers.count { ans -> ans.value == groupSize }
            answers = HashMap()
            groupSize = 0
        } else {
            it.toCharArray().forEach { c -> answers.put(c, answers.getOrDefault(c, 0) + 1) }
            groupSize++
        }

    }
    total += answers.count { ans -> ans.value == groupSize }
    return total
}