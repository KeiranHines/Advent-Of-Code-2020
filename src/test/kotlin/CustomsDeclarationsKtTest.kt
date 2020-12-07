import org.testng.Assert
import org.testng.annotations.Test

/**
 * TODO Add class/file description
 *
 * @author
 * @creation 6/12/2020
 */
class CustomsDeclarationsKtTest
{

    @Test
    fun customsDeclarationTest()
    {
        val input = example.split("\n")
        Assert.assertEquals(countUniqueDeclarations(input), 11)
    }

    @Test
    fun customsGroupDeclarationTest() {
        val input = example.split("\n")
        Assert.assertEquals(countCollectiveDeclarations(input), 6)
    }

    val example = """abc

a
b
c

ab
ac

a
a
a
a

b"""
}
