package DynamicProgramming

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
fun main(){
    val br = BufferedReader(InputStreamReader(System.`in`))
    val bw = BufferedWriter(OutputStreamWriter(System.out))

    val fibo = generateSequence(Pair(0,1)){Pair(it.second,it.first+it.second)}.map { it.first }
    repeat(br.readLine().toInt()){
        var n = br.readLine().toInt()
        val fiboList = ArrayList<Int>()
        fibo.takeWhile { it <=n }.forEach { fiboList.add(it) }
        fiboList.reverse()
//        println(fiboList.joinToString (" " ))
        val resultList = ArrayList<Int>()
        fiboList.forEach {
            if (it >0 && it <=n){
                resultList.add(it)
                n -= it
            }
        }
        resultList.reverse()
        bw.write(resultList.joinToString(" ")+"\n")
    }
    bw.flush()
    bw.close()
}