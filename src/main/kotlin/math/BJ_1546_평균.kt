package math

import java.util.StringTokenizer

fun main() = with(System.`in`.bufferedReader()){
    val n = readLine().toInt()
    val st = StringTokenizer(readLine())
    val ary = IntArray(n)
    repeat(n){
        ary[it] = st.nextToken().toInt()
    }
    val m = ary.max()
    var sum = 0.0
    repeat(n){
        sum += ary[it].toDouble()/m*100
    }
    println(sum/n)
}