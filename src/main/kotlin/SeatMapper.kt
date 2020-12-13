import kotlin.math.min

fun runSimulationTillComplete(seatMap: List<String>): List<String> {
    var last = seatMap
    var newMap = runSimulation(last)
    while (newMap != last) {
        last = newMap
        newMap = runSimulation(last)
    }
    return newMap
}

fun runSimulation(seatMap: List<String>): List<String> {
    val newMap = seatMap.toMutableList()
    seatMap.forEachIndexed { y, row ->
        val line = newMap.removeAt(y).toCharArray()
        row.forEachIndexed { x, seat ->
            line[x] = when (seat) {
                EMPTY -> handleEmpty(seatMap, x, y)
                OCCUPIED -> handleOccupied(seatMap, x, y)
                else -> FLOOR
            }
        }
        newMap.add(y, String(line))
    }
    return newMap
}

fun runAdvancedSimulationTillComplete(seatMap: List<String>): List<String> {
    var last = seatMap
    var newMap = runAdvancedSimulation(last)
    while (newMap != last) {
        last = newMap
        newMap = runAdvancedSimulation(last)
    }
    return newMap
}


fun runAdvancedSimulation(seatMap: List<String>): List<String> {
    val newMap = seatMap.toMutableList()
    seatMap.forEachIndexed { y, row ->
        val line = newMap.removeAt(y).toCharArray()
        row.forEachIndexed { x, seat ->
            val nextSeats = findNextSeats(seatMap, x, y)
            line[x] = when (seat) {
                EMPTY -> if (nextSeats.any { it == OCCUPIED }) EMPTY else OCCUPIED
                OCCUPIED -> if (nextSeats.count { it == OCCUPIED } >= 5) EMPTY else OCCUPIED
                else -> FLOOR
            }
        }
        newMap.add(y, String(line))
    }
    return newMap
}

private fun handleEmpty(seatMap: List<String>, x: Int, y: Int): Char {
    val line = seatMap[y]
    val checkLeft = x - 1 >= 0
    val checkRight = x + 1 < line.length

    if ((checkRight && line[x + 1] == OCCUPIED) || checkLeft && line[x - 1] == OCCUPIED) { // Right edge
        return EMPTY
    }
    if (y - 1 >= 0) { //Top edge
        val topLine = seatMap[y - 1]
        if (topLine[x] == OCCUPIED || (checkLeft && topLine[x - 1] == OCCUPIED) || (checkRight && topLine[x + 1] == OCCUPIED)) {
            return EMPTY
        }
    }
    if (y + 1 < seatMap.size) { // Bottom edge
        val bottomLine = seatMap[y + 1]
        if (bottomLine[x] == OCCUPIED || (checkLeft && bottomLine[x - 1] == OCCUPIED) || (checkRight && bottomLine[x + 1] == OCCUPIED)) { // Bottom centre
            return EMPTY
        }
    }
    return OCCUPIED

}

private fun handleOccupied(seatMap: List<String>, x: Int, y: Int): Char {
    val line = seatMap[y]
    val checkLeft = x - 1 >= 0
    val checkRight = x + 1 < line.length
    var adjacent = 0
    if (checkRight && line[x + 1] == OCCUPIED) {
        adjacent++
    }
    if (checkLeft && line[x - 1] == OCCUPIED) {
        adjacent++
    }
    if (y - 1 >= 0) { //Top edge
        val topLine = seatMap[y - 1]
        if (topLine[x] == OCCUPIED) {
            adjacent++
        }
        if (checkLeft && topLine[x - 1] == OCCUPIED) {
            adjacent++
        }
        if (checkRight && topLine[x + 1] == OCCUPIED) {
            adjacent++
        }
    }
    if (y + 1 < seatMap.size) { // Bottom edge
        val bottomLine = seatMap[y + 1]
        if (bottomLine[x] == OCCUPIED) { // Bottom centre
            adjacent++
        }
        if (checkLeft && bottomLine[x - 1] == OCCUPIED) {
            adjacent++
        }
        if (checkRight && bottomLine[x + 1] == OCCUPIED) {
            adjacent++
        }
    }
    return if (adjacent >= 4) EMPTY else OCCUPIED
}

private fun findNextSeats(seatMap: List<String>, x: Int, y: Int): List<Char> {
    val seatsAround = mutableListOf<Char>()
    val line = seatMap[y]
    processHorizontal(seatMap, y, x+1 until line.length, seatsAround)
    if(x > 0) {
        processHorizontal(seatMap, y, x-1 downTo 0, seatsAround)
    }
    processVertically(seatMap, x, y+1 until seatMap.size, seatsAround)
    if(y > 0) {
        processVertically(seatMap, x, y-1 downTo 0, seatsAround)
    }

    val topLeftLimit = min(x, y)
    for (z in (0 until topLeftLimit)) {
        val char = seatMap[y - (z + 1)][x - (z + 1)]
        if (char != FLOOR) {
            seatsAround.add(char)
            break
        }
    }

    val topRightLimit = min(line.length - 1 - x, y)
    for (z in (0 until topRightLimit)) {
        val char = seatMap[y - (z + 1)][x + (z + 1)]
        if (char != FLOOR) {
            seatsAround.add(char)
            break
        }
    }

    val bottomLeftLimit = min(x, seatMap.size - 1 - y)
    for (z in (0 until bottomLeftLimit)) {
        val char = seatMap[y + (z + 1)][x - (z + 1)]
        if (char != FLOOR) {
            seatsAround.add(char)
            break
        }
    }

    val bottomRightLimit = min(line.length - 1 - x, seatMap.size - 1 - y)
    for (z in (0 until bottomRightLimit)) {
        val char = seatMap[y + (z + 1)][x + (z + 1)]
        if (char != FLOOR) {
            seatsAround.add(char)
            break
        }
    }
    return seatsAround
}

private fun processHorizontal(seatMap: List<String>, y: Int, range: IntProgression, seatsAround: MutableList<Char>) {
    val line = seatMap[y]
    range.forEach {
        val char = line[it]
        if (char != FLOOR) {
            seatsAround.add(char)
            return
        }
    }
}

private fun processVertically(seatMap: List<String>, x: Int, range: IntProgression, seatsAround: MutableList<Char>) {
    for (y in range) {
        val char = seatMap[y][x]
        if (char != FLOOR) {
            seatsAround.add(char)
            return
        }
    }
}

const val EMPTY: Char = 'L'
const val OCCUPIED: Char = '#'
const val FLOOR: Char = '.'