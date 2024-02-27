import java.io.File

// Shared function to read data from a file
fun readFile(filePath: String): List<String> {
    return File(filePath).readLines()
}

// Function to process football data
fun processFootballData(lines: List<String>) {
    val teams = lines.mapNotNull { line ->
        val matchResult =
            Regex("""^\s*\d+\.\s+(\w+)\s+\d+\s+\d+\s+\d+\s+\d+\s+(\d+)\s+-\s+(\d+).*""").matchEntire(line)
        matchResult?.let {
            val (name, forGoals, againstGoals) = it.destructured
            Team(name, forGoals.toInt(), againstGoals.toInt())
        }
    }
    val teamWithSmallestDifference = teams.minByOrNull { it.goalDifference() }
    println("El equipo con la menor diferencia de goles es: ${teamWithSmallestDifference?.name}")
}

// Function to process weather data
fun processWeatherData(lines: List<String>) {
    var menorVariacion = Double.MAX_VALUE
    var numeroDiaMenorVariacion = 0
    for (line in lines) {
        val columns = line.trim().split(Regex("\\s+"))
        if (columns.size >= 3 && columns[0].toIntOrNull() != null) {
            val maxT = columns[1].toDoubleOrNull()
            val minT = columns[2].toDoubleOrNull()
            if (maxT != null && minT != null) {
                val variacion = maxT - minT
                if (variacion < menorVariacion) {
                    menorVariacion = variacion
                    numeroDiaMenorVariacion = columns[0].toInt()
                }
            }
        }
    }
    if (numeroDiaMenorVariacion != 0) {
        println("El día con la menor variación de temperatura es el día $numeroDiaMenorVariacion")
    } else {
        println("No se encontraron datos válidos en el archivo.")
    }
}

// Common function to process file based on type
fun processFile(filePath: String, type: FileType) {
    val lines = readFile(filePath)
    when (type) {
        FileType.FOOTBALL -> processFootballData(lines)
        FileType.WEATHER -> processWeatherData(lines)
    }
}

// Enum to represent file type
enum class FileType {
    FOOTBALL, WEATHER
}

// Data class to represent a football team
data class Team(val name: String, val forGoals: Int, val againstGoals: Int) {
    fun goalDifference() = Math.abs(forGoals - againstGoals)
}

fun main() {
    val footballFilePath = "src/football.dat"
    val weatherFilePath = "src/weather.dat"

    processFile(footballFilePath, FileType.FOOTBALL)
    processFile(weatherFilePath, FileType.WEATHER)
}