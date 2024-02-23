import java.io.File
import kotlin.math.abs

data class Teams(val name: String, val forGoals: Int, val againstGoals: Int) {
    fun goalDifference() = abs(forGoals - againstGoals)
}
fun main(){
 val files = File("C:\\Users\\ing29\\Downloads\\football.dat");
    val teams = mutableListOf<Teams>()

    files.forEachLine {line ->
        if (line.matches(Regex("""^\s*\d+\.\s+(\w+)\s+\d+\s+\d+\s+\d+\s+\d+\s+(\d+)\s+-\s+(\d+).*"""))){
            val matcResult = Regex("""^\s*\d+\.\s+(\w+)\s+\d+\s+\d+\s+\d+\s+\d+\s+(\d+)\s+-\s+(\d+).*""").matchEntire(line)!!

            val name = matcResult.groupValues[1]
            val forGoals = matcResult.groupValues[2].toInt()
            val againstGoals = matcResult.groupValues[3].toInt()
            teams.add(Teams(name, forGoals,againstGoals))
        }
    }
    val teamWithhSmallestDifference = teams.minByOrNull { it.goalDifference() }
    println("The team with smallest difference was $teamWithhSmallestDifference?.name")
}