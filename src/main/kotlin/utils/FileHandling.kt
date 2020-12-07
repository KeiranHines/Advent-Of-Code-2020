package utils

import java.nio.file.Files
import java.nio.file.Paths

fun readLinesFromFile(filename: String): List<String> {
    val projectDirAbsolutePath = Paths.get("").toAbsolutePath().toString()
    val path = Paths.get(projectDirAbsolutePath, "/src/main/resources/$filename")
    return path.toAbsolutePath().toFile().readLines()
}