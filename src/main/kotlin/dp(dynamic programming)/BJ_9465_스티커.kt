package `dp(dynamic programming)`

import java.io.StreamTokenizer

fun main() = with(StreamTokenizer(System.`in`.bufferedReader())){
    val readInt = { nextToken() ; nval.toInt() }
    val t = readInt()
    repeat(t){
        val n = readInt()
        val ary = Array(2){IntArray(n+1)}
        repeat(2){i ->
            repeat(n){j ->
                ary[i][j+1] = readInt()
            }
        }
        for (i in 2 .. n){
            ary[0][i] += maxOf(ary[1][i-1],ary[1][i-2])
            ary[1][i] += maxOf(ary[0][i-1],ary[0][i-2])
        }
        println(maxOf(ary[0][n],ary[1][n]))
    }
}