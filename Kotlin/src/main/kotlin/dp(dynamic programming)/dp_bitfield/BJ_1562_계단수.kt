package main.kotlin.`dp(dynamic programming)`.dp_bitfield

/**
 *  DFS로는 못 푸나..?
 *
 */
import java.io.StreamTokenizer

fun main() = with(StreamTokenizer(System.`in`.bufferedReader())){
    fun input() : Int {
        nextToken()
        return nval.toInt()
    }
    val n = input()
    val MOD = 1_000_000_000
    var result = 0
    val dp = Array(n +1){Array(10){IntArray( 1 shl 10)} }

    if(n in 1.. 9) result = 0
    else if (n == 10) result = 1
    else{
        for(i in 1..9){
            dp[1][i][1 shl i] = 1
        }
        for(i in 2..n){
            for(j in 0..9){
                for(k in 0 until (1 shl 10)){
                    val visited = k or (1 shl j)
                    when(j){
                        0 -> dp[i][j][visited] = (dp[i][j][visited] + dp[i-1][1][k]) % MOD
                        9 -> dp[i][j][visited] = (dp[i][j][visited] + dp[i-1][8][k]) % MOD
                        else -> dp[i][j][visited] = (dp[i][j][visited] + dp[i-1][j-1][k] + dp[i-1][j+1][k]) % MOD
                    }
                }
            }
        }
        for(i in 0..9){
            result = (result + dp[n][i][(1 shl 10)-1]) % MOD
        }
    }
    println(result)
}