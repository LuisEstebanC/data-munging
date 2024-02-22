import java.io.File


fun main() {
    val file = File("C:\\Users\\ing29\\Downloads\\weather.dat")
    val line = file.readLines();

    println(line)
}