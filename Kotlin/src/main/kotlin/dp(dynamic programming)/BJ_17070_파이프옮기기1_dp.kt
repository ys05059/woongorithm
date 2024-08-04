package `dp(dynamic programming)`

import java.io.StreamTokenizer

fun main() = with(StreamTokenizer(System.`in`.bufferedReader())) {
    fun input(): Int {
        nextToken()
        return nval.toInt()
    }

    val n = input()
    val graph = Array(n) { IntArray(n) { input() } }
    val dp = Array(n){ Array(n) { IntArray(3)} }  // 가로 0, 세로 1, 대각선 2
    dp[0][1][0]++

    for(y in 0 until n){
        for(x in 2 until n){
            if(graph[y][x] == 1) continue
            dp[y][x][0] = dp[y][x-1][0] + dp[y][x-1][2]         // 가로
            if(y == 0) continue
            dp[y][x][1] = dp[y-1][x][1] + dp[y-1][x][2]           // 세로
            if(graph[y-1][x] == 1 || graph[y][x-1] == 1) continue
            dp[y][x][2] = dp[y-1][x-1].sum()
        }
    }
    println(dp[n-1][n-1].sum())
}
