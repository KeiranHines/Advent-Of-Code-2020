
class NavigationSystem2(var waypointNorth: Int, var waypointEast: Int): NavigationSystem() {

    override fun processCommand(command: Command, numeric: Int) {
        when (command) {
            Command.N -> waypointNorth += numeric
            Command.S -> waypointNorth -= numeric
            Command.E -> waypointEast += numeric
            Command.W -> waypointEast -= numeric
            Command.F -> {
                north += waypointNorth * numeric
                east += waypointEast * numeric
            }
            else -> super.processCommand(command, numeric)
        }
    }

    override fun rotateRight(degrees: Int) {
        val turns = degrees /90
        for(turn in 1..turns)
        {
            val t = waypointNorth
            waypointNorth = -waypointEast
            waypointEast = t
        }
    }

    override fun rotateLeft(degrees: Int) {
        val turns = degrees / 90
        for (turn in 1..turns) {
            val t = waypointNorth
            waypointNorth = waypointEast
            waypointEast = -t
        }
    }
}