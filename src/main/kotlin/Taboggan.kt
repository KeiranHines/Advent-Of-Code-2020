
fun numTreesInStraightPath(map : List<String>, right: Int = 3, down : Int = 1) : Int
{
    var numTrees = 0
    var yPos = 0;
    var xPos = 0;
    while(yPos + down < map.size)
    {
        yPos += down;
        xPos += right;
        val currentLine = map[yPos]
        if(xPos < 0)
        {
            xPos += currentLine.length
        }
        if(xPos > currentLine.length-1)
        {
            xPos -= (currentLine.length)
        }
        if(isTree(currentLine[xPos]))
        {
            numTrees++
        }
    }
    return numTrees
}

fun isTree(char : Char): Boolean = char == TREE


private val TREE : Char = '#'