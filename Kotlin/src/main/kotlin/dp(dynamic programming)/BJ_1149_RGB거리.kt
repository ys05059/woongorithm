package `dp(dynamic programming)`

/**
 * DP 기본 문제 느낌
 * 바텀업 - 타블레이션 방식
 * 가능한 경우의 수 4가지 -> array로 만들고 하나씩 뽑아서 계산해보고 가장 작은값 넣기
 *
 */


import java.io.StreamTokenizer

fun main() = with(StreamTokenizer(System.`in`.bufferedReader())){
    fun input() : Int {
        nextToken()
        return nval.toInt()
    }

    val n = input()
    val dp = Array(n){IntArray(3){input()}}
    for(i in 1 until n){
        dp[i][0] += minOf(dp[i-1][1], dp[i-1][2])
        dp[i][1] += minOf(dp[i-1][0], dp[i-1][2])
        dp[i][2] += minOf(dp[i-1][0], dp[i-1][1])
    }
    println(minOf(dp[n-1][0],dp[n-1][1],dp[n-1][2]))
}
