import org.testng.Assert
import org.testng.annotations.Test

class JoltageAdapterKtTest {

    @Test
    fun findJoltageChange() {
        val list = example.split('\n').map { it.toInt() }
        val changes = findJoltageChanges(list)
        Assert.assertEquals(Pair(changes.first, changes.third), Pair(22, 10))
    }

    @Test
    fun findJoltageCombinations() {
        val list = simpleExample.split('\n').map { it.toInt() }
        val ans = findJoltageCombinations(list)
        Assert.assertEquals(ans, 8)
    }

    @Test
    fun findJoltageCombinations2() {
        val list = example.split('\n').map { it.toInt() }
        val ans = findJoltageCombinations(list)
        Assert.assertEquals(ans, 19208)
    }


    val simpleExample = """16
10
15
5
1
11
7
19
6
12
4"""

    val example = """28
33
18
42
31
14
46
20
48
47
24
23
49
45
19
38
39
11
1
32
25
35
8
17
7
9
4
2
34
10
3"""
}