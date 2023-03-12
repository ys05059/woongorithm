import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.lang.Exception
import kotlin.math.min

fun main() {

    val br = BufferedReader(InputStreamReader(System.`in`))
    val bw = BufferedWriter(OutputStreamWriter(System.out))


    while(true){
        val a = br.readLine() ?:break
        val b = br.readLine()

        val aList = MutableList(26){0}
        val bList = MutableList(26){0}
        for (c in a.toCharArray()){
            aList[(c-97).code] +=1
        }
        for (c in b.toCharArray()){
            bList[(c-97).code] +=1
        }

        var result = ""
        for(i in 0 until 26){
            result += (i + 97).toChar().toString().repeat(min(aList[i], bList[i]))
        }
        bw.write(result+"\n")

//        println(result)
    }
    bw.flush()
}


//fun main() = System.`in`.bufferedReader().run {
//    System.out.bufferedWriter().run {
//        while(true){
//            // 알파벳 갯수를 저장할 배열 a와 b를 초기화합니다.
//            val a = IntArray(26)
//            val b = IntArray(26)
//
//            // 첫번째 문자열을 읽어서 알파벳 갯수를 카운트합니다.
//            (readLine() ?: return flush()).forEach { a[it - 'a']++ }
//
//            // 두번째 문자열을 읽어서 알파벳 갯수를 카운트합니다.
//            readLine().forEach { b[it - 'a']++ }
//
//            // 두 문자열에서 공통으로 가지고 있는 알파벳을 찾아 출력합니다.
//            repeat(26) { i ->
//                repeat(minOf(a[i], b[i])) { write(97 + i) }
//            }
//            newLine()
//        }
//    }
//}