import java.io.File


fun main() {
    val file = File("C:\\Users\\ing29\\Downloads\\weather.dat")
    val lines = file.readLines();

    var minunVariation = Double.MAX_VALUE;
    var dayNumber =0;

    for (line in lines){
        val columns = line.trim().split(Regex("\\s+"))

        println(columns)

//        if (columns.size >= 3 && columns[0].toIntOrNull() != null){
//            val maxT
//        }


    }
}