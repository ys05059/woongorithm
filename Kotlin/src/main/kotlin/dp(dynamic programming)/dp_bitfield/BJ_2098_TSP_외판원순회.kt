package main.kotlin.`dp(dynamic programming)`.dp_bitfield

import java.io.StreamTokenizer

fun main() = with(StreamTokenizer(System.`in`.bufferedReader())){
    fun input() : Int {
        nextToken()
        return nval.toInt()
    }
    val INF = 16_000_001
    val n = input()
    val cost = Array(n) { IntArray(n) { input()} }
    val dp = Array(n) { IntArray((1 shl n)-1) { -1 } }

    fun dfs(e : Int, visited : Int) : Int{
        if(visited == (1 shl n)-1){
            if(cost[e][0] == 0) return INF
            else return cost[e][0]
        }
        if(dp[e][visited] != -1) return dp[e][visited]
        dp[e][visited] = INF
        for(i in 0 until n){
            if(cost[e][i] == 0) continue
            if(visited and (1 shl i) != 0) continue
            dp[e][visited] = minOf(dfs(i, visited or (1 shl i))+ cost[e][i],dp[e][visited])
        }
        return dp[e][visited]
    }
    println(dfs(0,1))
}