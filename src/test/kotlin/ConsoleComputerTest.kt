import org.testng.Assert
import org.testng.annotations.Test

class ConsoleComputerTest {
    @Test
    fun exampleConsoleComputer() {
        val c = ConsoleComputer()
        val list = example.split('\n')
        c.runProgramTillRepeat(list)
        Assert.assertEquals(c.accumulator, 5)
    }

    @Test
    fun findBugTest() {
        val c = ConsoleComputer()
        val list = example.split('\n')
        val safe = c.fixBug(list)
        c.runProgramExit(safe)
        Assert.assertEquals(c.accumulator, 8)
    }

    val example = """nop +0
acc +1
jmp +4
acc +3
jmp -3
acc -99
acc +1
jmp -4
acc +6"""
}