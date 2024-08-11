package main.kotlin.`dp(dynamic programming)`

import java.io.StreamTokenizer

fun main() = with(StreamTokenizer(System.`in`.bufferedReader())){
    fun input() : Int {
        nextToken()
        return nval.toInt()
    }

    val n = input()
    val ary = IntArray(n){input()}
    val dp = IntArray(n){1}

    for(i in 1 until n){
        for(j in 0 until i){
            if(ary[j] < ary[i]){
                dp[i] = maxOf(dp[i],dp[j]+1)
            }
        }
    }

    println(dp.max())
}