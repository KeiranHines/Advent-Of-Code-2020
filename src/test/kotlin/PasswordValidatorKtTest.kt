import org.testng.Assert
import org.testng.annotations.Test

@Test
class PasswordValidatorKtTest {

    @Test
    fun isPasswordValid1() {
        val testValidPass = "1-3 a: abcde"
        Assert.assertTrue(isPasswordValid(testValidPass))
    }

    @Test
    fun isPasswordInvalid() {
        val testInvalidPass = "1-3 b: cdefg"
        Assert.assertFalse(isPasswordValid(testInvalidPass))
    }

    @Test
    fun isPasswordValid2() {
        val testValidPass = "2-9 c: ccccccccc"
        Assert.assertTrue(isPasswordValid(testValidPass))
    }

    @Test
    fun isPasswordCorrect() {
        val testValidPass = "1-3 a: abcde"
        Assert.assertTrue(isPasswordValid(testValidPass))
    }

    @Test
    fun isPasswordIncorrect1() {
        val testInvalidPass = "1-3 b: cdefg"
        Assert.assertFalse(isPasswordCorrect(testInvalidPass))
    }

    @Test
    fun isPasswordIncorrect2() {
        val testInvalidPass = "2-9 c: ccccccccc"
        Assert.assertFalse(isPasswordCorrect(testInvalidPass))
    }
}