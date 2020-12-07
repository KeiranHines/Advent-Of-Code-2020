fun passportHasRequiredFields(passport: Map<String, String>): Boolean {
    return passport.keys.size == 8 || (passport.keys.size == 7 && !passport.containsKey("cid"))
}

fun isPassportValid(passport: Map<String,String>) : Boolean {
    val height = passport["hgt"]
    val hairColor = passport.getOrDefault("hcl", "invalid")
    val heightRange = if(height?.contains("cm")!!) 150..193 else 59..76
    return try {
        passport["byr"]?.toInt() in 1920..2002 &&
                passport["iyr"]?.toInt() in 2010..2020 &&
                passport["eyr"]?.toInt() in 2020..2030 &&
                Integer.parseInt(height.subSequence(0,height.length-2).toString()) in heightRange &&
                hairColor.startsWith('#') && hairColor.count { it in colorNumRng || it in colorCharRng } == 6 &&
                eyeList.contains(passport["ecl"]) &&
                passport["pid"]?.length == 9 && passport["pid"]?.toIntOrNull() != null
    } catch (e : Exception)
    {
        false
    }
}

fun parsePassportEntries(data: List<String>): List<Map<String, String>> {
    val lineBreaks = ArrayList<Int>()
    data.forEachIndexed { index, s ->
        if (s.isBlank()) {
            lineBreaks.add(index)
        }
    }
    val passports = ArrayList<Map<String, String>>()
    lineBreaks.forEachIndexed { index, end ->
        val start = if (index == 0) 0 else lineBreaks[index - 1] + 1
        val passport = splitPassport(data.subList(start, end).joinToString(separator = " ") { it.trim() })

        passports.add(passport)
    }
    val passport = splitPassport(
        data.subList(lineBreaks[lineBreaks.size - 1] + 1, data.size).joinToString(separator = " ") { it.trim() })
    passports.add(passport)


    return passports
}

fun splitPassport(passport: String): Map<String, String> {
    val details = HashMap<String, String>()
    passport.split(' ').forEach {
        val keyValue = it.split(':')
        details[keyValue[0]] = keyValue[1]
    }
    return details
}

private val colorNumRng= '0'..'9'
private val colorCharRng= 'a'..'z'
private val eyeList = listOf("amb", "blu", "brn", "gry", "grn", "hzl", "oth")