import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val bw = BufferedWriter(OutputStreamWriter(System.out))

    val (n, m) = br.readLine()!!.split(" ").map { it.toInt() }
    val NameMap = HashMap<String, Int>()
    val NumMap = HashMap<Int, String>()
    repeat(n) {
        val input = br.readLine()!!
        NameMap[input] = it + 1
        NumMap[it + 1]= input
    }
    val numberPattern = "\\d+".toRegex()
    val textPattern = "\\D+".toRegex()
    repeat(m) {
        val q = br.readLine()!!
        when {
            numberPattern.matches(q) -> bw.write("${NumMap[q.toInt()]}\n")
            textPattern.matches(q) -> bw.write(NameMap[q].toString() +"\n")
        }
    }

    bw.flush()
    bw.close()
}