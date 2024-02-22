import java.io.File


fun main() {
    val file = File("C:\\Users\\ing29\\Downloads\\weather.dat")
    val lines = file.readLines();

    var minimumVariation = Double.MAX_VALUE;
    var dayNumber =0;

    for (line in lines){
        val columns = line.trim().split(Regex("\\s+"))

        if (columns.size >= 3 && columns[0].toIntOrNull() != null){
            val maxT = columns[1].toDoubleOrNull()
            val minT = columns[2].toDoubleOrNull()

            if (maxT !=null && minT !=null){
                val variation = maxT - minT;
                if (minimumVariation > variation){
                    minimumVariation = variation
                    dayNumber = columns[0].toInt();
                }
            }
        }
    }

   println("The minimum variation was $minimumVariation on day $dayNumber")

}