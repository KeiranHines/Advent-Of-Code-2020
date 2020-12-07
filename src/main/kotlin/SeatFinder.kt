fun findSeatId(input: String, _minRow: Int = 0, _maxRow: Int = 127, _minCol: Int = 0, _maxCol: Int = 7): Int {
    var minRow = _minRow
    var maxRow = _maxRow
    var minCol = _minCol
    var maxCol = _maxCol
    input.forEach {
        when (it) {
            'F' -> maxRow = minRow + (maxRow - minRow) / 2
            'B' -> minRow += (maxRow - minRow + 1) / 2
            'L' -> maxCol = minCol + (maxCol - minCol) / 2
            'R' -> minCol += (maxCol - minCol + 1) / 2
        }
    }
    return maxRow * 8 + maxCol
}