
open class NavigationSystem(var north: Int = 0, var east : Int = 0, var facing : Command = Command.E) {

    fun processCommands(input: List<String>): Pair<Int, Int> {
        input.forEach { processCommand(Command.valueOf(it[0].toString()), it.substring(1).toInt()) }
        return Pair(east, north)
    }

    protected open fun processCommand(command: Command, numeric: Int) {
        when (command) {
            Command.N -> north += numeric
            Command.S -> north -= numeric
            Command.E -> east += numeric
            Command.W -> east -= numeric
            Command.F -> processCommand(facing, numeric)
            Command.L -> rotateLeft(numeric)
            Command.R -> rotateRight(numeric)
        }
    }

    protected open fun rotateLeft(degrees: Int) {
        val turns = degrees / 90
        for(turn in 1..turns)
        {
            facing = when(facing)
            {
                Command.N -> Command.W
                Command.W -> Command.S
                Command.S -> Command.E
                Command.E -> Command.N
                else -> facing
            }
        }
    }

    protected open fun rotateRight(degrees: Int) {
        val turns = degrees / 90
        for (turn in 1..turns) {
            facing = when (facing) {
                Command.N -> Command.E
                Command.E -> Command.S
                Command.S -> Command.W
                Command.W -> Command.N
                else -> facing
            }
        }
    }

    enum class Command {
        N ,S, E, W,
        L, R,
        F
    }
}