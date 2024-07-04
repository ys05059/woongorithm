package `dp(dynamic programming)`

/**
 * n개 2칸짜리 만들어야한다
 */

import java.io.StreamTokenizer
fun main() = with(StreamTokenizer(System.`in`.bufferedReader())) {
    fun input(): Int {
        nextToken()
        return nval.toInt()
    }

    val n = input()
    val dp = Array(n) {
        Array(it + 1) {
            val temp = input()
            IntArray(2) { temp }
        }
    }

    for(i in 1 until n){
        for(j in 0..i){
            if(j > 0) dp[i][j][0] += maxOf(dp[i-1][j-1][0],dp[i-1][j-1][1])
            if(j < i) dp[i][j][1] += maxOf(dp[i-1][j][0],dp[i-1][j][1])
        }
    }

    var result = 0
    for(i in 0 until n){
        result = result.coerceAtLeast(maxOf(dp[n-1][i][0],dp[n-1][i][1]))
    }
    println(result)
}