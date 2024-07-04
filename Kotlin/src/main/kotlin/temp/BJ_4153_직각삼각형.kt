package temp

import kotlin.math.pow
fun main() = with(System.`in`.bufferedReader()){
    while (true){
        val input = readLine().split(" ").map { it.toDouble() }.sorted()
        if (input[0].toInt() == 0) break
        if (input[0].pow(2) + input[1].pow(2) == input[2].pow(2)) println("right") else println("wrong")
    }

}