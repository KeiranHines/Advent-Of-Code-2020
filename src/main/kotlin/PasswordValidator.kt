
fun isPasswordValid(hash : String) : Boolean{
    val sections = hash.split('-', ' ', ':')
    val min = sections[0].toInt()
    val max = sections[1].toInt()
    val letter = sections[2][0]
    // Sections 3 is a null element
    val password = sections[4]
    return password.count { it == letter } in min..max;
}

fun isPasswordCorrect(hash : String) : Boolean {
    val sections = hash.split('-', ' ', ':')
    val min = sections[0].toInt()
    val max = sections[1].toInt()
    val letter = sections[2][0]
    // Sections 3 is a null element
    val password = sections[4]
    return (password[min-1] == (letter)) xor (password[max-1] == letter)
}
