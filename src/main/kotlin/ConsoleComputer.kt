class ConsoleComputer {
    var accumulator = 0

    fun runProgramTillRepeat(instructions: List<String>) : List<Int> {
        val seen = mutableListOf<Int>()
        var currentIndex = 0
        while (true) {
            if (seen.contains(currentIndex)) {
                return seen
            }
            seen.add(currentIndex)
            currentIndex += parseInstruction(instructions[currentIndex])
        }
    }

    fun runProgramExit(instructions: List<String>, initial : Int = 0) : Boolean {
        var currentIndex = initial
        val seen = mutableListOf<Int>()

        while (currentIndex < instructions.size) {
            if (seen.contains(currentIndex)) {
                return false
            }
            seen.add(currentIndex)
            currentIndex += parseInstruction(instructions[currentIndex])
        }
        return true
    }

    private fun parseInstruction(instruction: String): Int =
        instructionRegex.find(instruction)!!.destructured.let { (command, sign, stepSize) ->
            return when (command) {
                "acc" -> {
                    handleAccumulator(sign, stepSize.toInt())
                    1
                }
                "jmp" -> handleJump(sign, stepSize.toInt())
                else -> 1 // nop to make exhaustive
            }
        }


    fun fixBug(instructions: List<String>): List<String> {
        val copy = instructions.toMutableList()
        val bugLine = findBug(instructions)
        val bug = copy.removeAt(bugLine)
        if (bug.startsWith("nop")) {
            copy.add(bugLine, bug.replace("nop", "jmp"))
        } else if (bug.startsWith("jmp")) {
            copy.add(bugLine, bug.replace("jmp", "nop"))
        }
        return copy
    }

    private fun findBug(instructions: List<String>): Int {
        val simulator = ConsoleComputer()
        val options = simulator.runProgramTillRepeat(instructions)
        options.forEach {
            val instruction = instructions[it]
            instructionRegex.find(instruction)!!.destructured.let { (command, sign, stepSize) ->
                when (command) {
                    "nop" -> {
                        if(sign == "+" && stepSize.toInt() + it == instructions.size)
                        {
                            return it
                        }
                    }
                    "jmp" -> {
                        val copy = instructions.toMutableList()
                        val bug = copy.removeAt(it)
                        copy.add(it, bug.replace("jmp", "nop"))
                        if(simulator.runProgramExit(copy, it))
                        {
                            return it
                        }
                    }
                }
            }
        }
        return -1
    }

    private fun handleAccumulator(sign: String, amount: Int) {
        accumulator += if (sign == "-") -1 * amount else amount
    }

    private fun handleJump(sign: String, amount: Int) =
        if (sign == "-") -amount else amount


    companion object {
        val instructionRegex = Regex("(.*?) ([+|-])(\\d+)")
    }
}